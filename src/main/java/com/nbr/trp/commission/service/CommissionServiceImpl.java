package com.nbr.trp.commission.service;

import com.nbr.trp.commission.entity.Commission;
import com.nbr.trp.commission.repository.CommissionRepository;
import com.nbr.trp.ledger.entity.Ledger;
import com.nbr.trp.ledger.entity.Metrics;
import com.nbr.trp.ledger.repository.MetricsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CommissionServiceImpl implements CommissionService {

    @Autowired
    CommissionRepository commissionRepository;

    @Autowired
    MetricsRepository metricsRepository;


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


}

