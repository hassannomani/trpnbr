package com.nbr.trp.ledger.service;

import com.nbr.trp.commission.service.CommissionService;
import com.nbr.trp.commission.service.ItemsService;
import com.nbr.trp.ledger.entity.Ledger;
import com.nbr.trp.ledger.repository.LedgerRepository;
import com.nbr.trp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements  BillService{

    @Autowired
    LedgerRepository ledgerRepository;

    @Autowired
    ItemsService itemsService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommissionService commissionService;

    @Override
    public List<Ledger> getAgentBillableList(String agent) {
        return ledgerRepository.findAgentBillable(agent);
    }

    @Override
    public List<Ledger> getTRPBillableList(String trp) {
        return ledgerRepository.findTRPBillable(trp);
    }
}
