package com.nbr.trp.commission.service;

import com.nbr.trp.commission.entity.Commission;
import com.nbr.trp.commission.entity.CommissionBillView;
import com.nbr.trp.commission.repository.CommissionRepository;
import com.nbr.trp.commission.request.ValidateRequest;
import com.nbr.trp.ledger.entity.Ledger;
import com.nbr.trp.ledger.entity.Metrics;
import com.nbr.trp.ledger.repository.LedgerRepository;
import com.nbr.trp.ledger.repository.MetricsRepository;
import com.nbr.trp.user.entity.Role;
import com.nbr.trp.user.entity.User;
import com.nbr.trp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommissionServiceImpl implements CommissionService {

    @Autowired
    CommissionRepository commissionRepository;

    @Autowired
    MetricsRepository metricsRepository;

    @Autowired
    LedgerRepository ledgerRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public Commission saveCommission(Commission commission) {
        Commission com = commissionRepository.save(commission);
        return  com;
    }

    @Override
    public Commission getCommissionByCommissionNo(String id) {
        return null;
    }

    @Override
    public List<Commission> getCommissionByDebitCode(String code) {
        return null;
    }

    @Override
    public List<Commission> getCommissionByCreditCode(String code) {
        return commissionRepository.findByCreditCode(code);
    }

    public HashMap<String, String> calculateCommission(Ledger ld){
        String assessmentYear = ld.getAssessmentYear();
        String year = ld.getYearNo();
        double[] rate = new double[5];   //preparing rate array
        double[] amount = new double[5]; //prerparing amount array
        List<Metrics> list = metricsRepository.findByAssessmentYearAndYearNoOrderBySlotNo(assessmentYear, Integer.parseInt(year));
        for(int i =0;i<list.size();i++){
            double temp = (list.get(i).agentRate/100)+(list.get(i).representativeRate/100);
            rate[i] = temp;
            amount[i] = list.get(i).taxAmount;
        }

        for(int i=0;i<4;i++){
            System.out.println("amount "+ amount[i]);
            System.out.println("rate "+rate[i]);
        }
        amount[0]= Double.parseDouble(ld.getMinimumTax());  //initiating start index
        double sum = 0;         //contain commission
        double paidAmount = Double.parseDouble(ld.getPaidAmount());  // calculationn starts now
        double rest = 0;   //contain what is left after subtracting the minimum tax and its companions
        String remarks = "";

        if(paidAmount>amount[0]){   //paid amount is greater than the amount of minimum tax
            rest = paidAmount - amount[0];
            if(rest>amount[1]){
                sum = amount[0] *rate[0] + (amount[1]*rate[1]);
                remarks = "Added commission for amount "+String.format("%.2f", amount[0])+ " at rate "+ String.format("%.2f", rate[0])+". ";
                remarks+= "Added commission for amount "+String.format("%.2f", amount[1])+ " at rate "+ String.format("%.2f", rate[1])+". ";

                rest = paidAmount - amount[0] - amount[1];
                if(rest>amount[2]){
                    sum = sum+ (amount[2]*rate[2]);
                    rest = rest - amount[2];
                    sum = sum + (rest*rate[3]);
                    remarks+= "Added commission for amount "+String.format("%.2f", amount[2])+ " at rate "+ String.format("%.2f", rate[2])+". ";
                    remarks+= "Added commission for amount "+String.format("%.2f", rest)+ " at rate "+ String.format("%.2f", rate[3])+". ";

                }else{
                    sum= sum+ (rest*rate[2]);
                    remarks+= "Added commission for amount "+String.format("%.2f", rest)+ " at rate "+ String.format("%.2f", rate[2])+". ";

                }
            } else {
                sum = amount[0] *rate[0] + (paidAmount-amount[0])*rate[1];
                remarks = "Added commission for amount "+String.format("%.2f", amount[0])+ " at rate "+ String.format("%.2f", rate[0])+". ";
                remarks+= "Added commission for amount "+String.format("%.2f", (paidAmount-amount[0]))+ " at rate "+ String.format("%.2f", rate[1])+". ";

            }
        }else{   //otherwise
            sum = paidAmount * (rate[0]);
            remarks = "Added commission for amount "+String.format("%.2f", paidAmount)+ " at rate "+ String.format("%.2f", rate[0])+". ";
        }
        HashMap <String, String> hashMap= new HashMap<>();
        hashMap.put("sum", String.valueOf(sum));
        hashMap.put("remarks", remarks);
        return hashMap;

    }

    public List<Commission> getAll(){
        return commissionRepository.findAll();
    }

    public Boolean SaveBulkCommission(String role, String tin, String[] ids){
        List<Commission> cm = new ArrayList<>();
        for(int i=0;i<ids.length;i++){
            Commission cm_individual = new Commission();
            cm_individual.setPayee(tin);
            cm_individual.setLedgerId(ids[i]);
            cm_individual.setPayeeType(role);
            cm_individual.setStatus("0");
            cm.add(cm_individual);
        }
        commissionRepository.saveAll(cm);
        return true;
    }

    public HashMap<String, String> valiDateCommission(ValidateRequest[] reqs){
        HashMap<String, String> map = new HashMap<>();
        for(int i=0;i< reqs.length;i++){
            String ledger = reqs[i].getLedger();
            String role = reqs[i].getRole();
            Integer count = commissionRepository.findDuplicatePayment(ledger,role);
            map.put(reqs[i].getId(),count.toString());
        }
        return map;
    }

    public Boolean approveBills(ValidateRequest[] reqs){
        List<Commission> cm = new ArrayList<>();
        for(int i=0;i< reqs.length;i++) {
            Commission cm_individual = commissionRepository.findByCreationNo(reqs[i].getId());
            cm_individual.setStatus("1");
            cm_individual.setPaymentDate(new Date());
            cm.add(cm_individual);
        }
        commissionRepository.saveAll(cm);
        return true;
    }

    public Boolean rejectBills(ValidateRequest[] reqs){
        List<Commission> cm = new ArrayList<>();
        List<Ledger> ls = new ArrayList<>();
        for(int i=0;i< reqs.length;i++) {
            Commission cm_individual = commissionRepository.findByCreationNo(reqs[i].getId());
            Ledger ledger = ledgerRepository.findByLid(reqs[i].getLedger());
            cm_individual.setStatus("-1");
            cm_individual.setPaymentDate(new Date());
            cm.add(cm_individual);
            if(reqs[i].getRole().equals("ROLE_AGENT"))
                ledger.setBillSubmittedAg("0");
            else if(reqs[i].getRole().equals("ROLE_REPRESENTATIVE"))
                ledger.setBillSubmittedTrp("0");
            ls.add(ledger);

        }
        commissionRepository.saveAll(cm);
        ledgerRepository.saveAll(ls);
        return true;
    }

    public List<CommissionBillView> getPendingBill(String tin){
        String pos = getRole(tin);

        //System.out.println(pos);
        if(pos.equals("ROLE_ADMIN"))
            return commissionRepository.findBillAdmin("0");
        else if(pos.equals("ROLE_AGENT") || pos.equals("ROLE_REPRESENTATIVE"))
            return commissionRepository.findBillUser(tin,"0");
        else return null;
    }

    public List<CommissionBillView> getRejectedBill(String tin){

        String pos = getRole(tin);

        if(pos.equals("ROLE_ADMIN"))
            return commissionRepository.findBillAdmin("-1");
        else if(pos.equals("ROLE_AGENT") || pos.equals("ROLE_REPRESENTATIVE"))
            return commissionRepository.findBillUser(tin,"-1");
        else return null;

    }

    public List<CommissionBillView> getApprovedBill(String tin){

        String pos = getRole(tin);

        if(pos.equals("ROLE_ADMIN"))
            return commissionRepository.findBillAdmin("1");
        else if(pos.equals("ROLE_AGENT") || pos.equals("ROLE_REPRESENTATIVE"))
            return commissionRepository.findBillUser(tin,"1");
        else return null;

    }

    private String getRole(String tin){
        User u = userRepository.getByTin(tin);
        Set<Role> role = u.getRoles();
        Role role1 = role.iterator().next();
        return role1.getName();
    }







}

