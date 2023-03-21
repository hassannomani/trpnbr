package com.nbr.trp.ledger.service;

import com.nbr.trp.ledger.entity.Ledger;
import com.nbr.trp.ledger.repository.LedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Service
public class LedgerServiceImpl implements  LedgerService
{
    @Autowired
    LedgerRepository ledgerRepository;

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
        return ledgerRepository.findByAgentId(id);
    }

    public List<Ledger> getLedgersOfARepresentative(String id){
        return ledgerRepository.findByRepresentativeId(id);
    }

    public List<Ledger> getLedgersOfAdmin(){
        return ledgerRepository.findAll();
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

}
