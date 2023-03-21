package com.nbr.trp.ledger.service;

import com.nbr.trp.ledger.entity.Ledger;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface LedgerService {

    public Ledger saveLedger(Ledger ledger);

    public Ledger getLedgerByTin(String tin) ;

    public List<Ledger> getLedgerWithinRange(String start, String end);

    public List<Ledger> getAll();

    public List<Ledger> getLadgersOfAnAgent(String id);
    public List<Ledger> getLedgersOfARepresentative(String id);

    public List<Ledger> getLedgersOfAdmin();
}
