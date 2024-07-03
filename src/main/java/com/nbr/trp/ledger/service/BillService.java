package com.nbr.trp.ledger.service;

import com.nbr.trp.ledger.entity.Ledger;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BillService {
    public List<Ledger> getAgentBillableList (String agent);

    public List<Ledger> getTRPBillableList (String agent);

    public Boolean saveBill(String role, String[] ids, String tin);
<<<<<<< HEAD
=======

>>>>>>> 65ac1a815693facce2fb795c8d3c2a90b055db34
}
