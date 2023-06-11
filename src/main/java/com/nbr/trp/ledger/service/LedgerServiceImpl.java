package com.nbr.trp.ledger.service;

import com.nbr.trp.commission.entity.Commission;
import com.nbr.trp.commission.entity.Items;
import com.nbr.trp.commission.exception.ItemNotFoundException;
import com.nbr.trp.commission.service.CommissionService;
import com.nbr.trp.commission.service.ItemsService;
import com.nbr.trp.ledger.entity.Ledger;
import com.nbr.trp.ledger.repository.LedgerRepository;
import com.nbr.trp.user.entity.User;
import com.nbr.trp.user.repository.UserRepository;
import com.nbr.trp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class LedgerServiceImpl implements  LedgerService
{
    @Autowired
    LedgerRepository ledgerRepository;

    @Autowired
    ItemsService itemsService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommissionService commissionService;


    @Override
    public Ledger saveLedger(Ledger ledger) {

        Ledger ldg = ledgerRepository.save(ledger);
        return ldg;
    }

    @Override
    public Ledger getLedgerByTin(String tin) {
        return null;
    }

    @Override
    public List<Ledger> getAll() {
        return null;
    }

    @Override
    public List<Ledger> getLadgersOfAnAgent(String id){
        return ledgerRepository.findByAgentTin(id);
    }

    public List<Ledger> getLedgersOfARepresentative(String id){
        return ledgerRepository.findByRepresentativeTin(id);
    }

    public List<Ledger> getLedgersOfAdmin(){
        return ledgerRepository.findAll();
    }

    @Override
    public List<Ledger> getLedgersOfARepresentativeRange(String id, String start, String end) {
        java.sql.Timestamp t1 = convertStringToTimestamp(start,0);
        java.sql.Timestamp t2 = convertStringToTimestamp(end,1);
        return ledgerRepository.findAllReprstvLedgerWithinRange(id, t1, t2);
    }

    @Override
    public List<Ledger> getLedgersOfAnAgentRange(String id, String start, String end) {
        java.sql.Timestamp t1 = convertStringToTimestamp(start,0);
        java.sql.Timestamp t2 = convertStringToTimestamp(end,1);
        return ledgerRepository.findAllAgentLedgerWithinRange(id, t1, t2);
    }

    @Override
    public List<Ledger> getLedgerWithinRange(String start, String end){
        java.sql.Timestamp t1 = convertStringToTimestamp(start,0);
        java.sql.Timestamp t2 = convertStringToTimestamp(end,1);
        System.out.println("timestamp 1"+t1);
        System.out.println("timestamp 2"+t2);
        return ledgerRepository.findAllWithinDateRange(t1, t2);
    }

    public java.sql.Timestamp convertStringToTimestamp(String strDate, int flag) {
//        java.sql.Timestamp t1 = java.sql.Timestamp.valueOf(strDate);
//        return t1;
        try {
            LocalDateTime dateTime;
            if(flag ==0)
                dateTime = LocalDate.parse(strDate).atStartOfDay();
            else
                dateTime = LocalDate.parse(strDate).atTime(LocalTime.MAX);

            return Timestamp.valueOf(dateTime);
        } catch(Exception e) {
            // look the origin of excption
            System.out.println(e);
            return null;
        }
    }

    public void checkItems(Ledger ledger){
        Items trpitem = itemsService.getItemsByCode(ledger.getRepresentativeTin());
        Items agentitem = itemsService.getItemsByCode(ledger.getAgentTin());

        if(trpitem==null){
            User trp = userRepository.getByTin(ledger.getRepresentativeTin());
            Items trpItem = new Items();
            trpItem.setItemCode(ledger.getRepresentativeTin());
            trpItem.setItemHead(trp.getFirstName()+trp.getLastName());
            trpItem.setItemNature("2");
            trpItem.setItemDescription("TRP");
            trpItem.setItemGrpHead("");
            itemsService.saveItems(trpItem);
            System.out.println("TRP Entered");

        }
        if(agentitem==null){
            User ag = userRepository.getByTin(ledger.getAgentTin());
            Items trpItem = new Items();
            trpItem.setItemCode(ledger.getAgentTin());
            trpItem.setItemHead(ag.getFirstName()+ag.getLastName());
            trpItem.setItemNature("2");
            trpItem.setItemDescription("Agent");
            trpItem.setItemGrpHead("");
            itemsService.saveItems(trpItem);
            System.out.println("Agent Entered");
        }
    }

    public void saveCommission(Ledger ledger) throws ItemNotFoundException{
        Items trp = itemsService.getItemsByCode(ledger.getRepresentativeTin());
        Items agent = itemsService.getItemsByCode(ledger.getAgentTin());
        Items bank = itemsService.getItemsByNature("1");
        HashMap<String, String> returnedVal = commissionService.calculateCommission(ledger);
        Double commission = Double.valueOf(returnedVal.get("sum"));
        String remarks = returnedVal.get("remarks");
        if(bank.getItemCode()!=null){
            if(trp!=null){
                Commission cmtrp = new Commission();
                cmtrp.setDebitCode(bank.getItemCode());
                cmtrp.setCreditCode(trp.getItemCode());
                cmtrp.setAmount(String.valueOf(commission*0.9));
                cmtrp.setRemarks(remarks);
                Commission cmsaved = commissionService.saveCommission(cmtrp);

            }
            if(agent!=null){
                Commission cmag = new Commission();
                cmag.setDebitCode(bank.getItemCode());
                cmag.setCreditCode(agent.getItemCode());
                cmag.setAmount(String.valueOf(commission*0.1));
                cmag.setRemarks(remarks);
                Commission cmsaved = commissionService.saveCommission(cmag);
            }
        }else {
            throw new ItemNotFoundException("Items not found");
        }

    }

    public List<Ledger> getByAssmentYrAndTin(String assmnt, String tin){
        List<Ledger> ld = ledgerRepository.findByAssessmentYearAndTaxpayerId(assmnt,tin);
        return ld;
    }



}
