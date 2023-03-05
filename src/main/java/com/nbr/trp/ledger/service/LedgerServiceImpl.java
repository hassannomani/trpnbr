package com.nbr.trp.ledger.service;

import com.nbr.trp.ledger.entity.Ledger;
import com.nbr.trp.ledger.repository.LedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public Ledger getPaymentByTinAndYear(String Tin, String year) {
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
}
