package com.nbr.trp.ledger.service;

import com.nbr.trp.commission.service.CommissionService;
import com.nbr.trp.commission.service.ItemsService;
import com.nbr.trp.ledger.entity.Ledger;
import com.nbr.trp.ledger.repository.LedgerRepository;
import com.nbr.trp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public Boolean saveBill(String role, String[] ids, String tin){

        Boolean bool= commissionService.SaveBulkCommission(role,tin,ids);
        if(bool){
            List<Ledger> ls = new ArrayList<>();
            for(int i =0;i<ids.length;i++){
                Ledger ld = ledgerRepository.findByLid(ids[i]);
                System.out.println(role.equals("ROLE_AGENT"));
                System.out.println(role);
                if(role.equals("ROLE_AGENT"))
                    ld.setBillSubmittedAg("1");
                else
                    ld.setBillSubmittedTrp("1");
                ls.add(ld);
            }
            ledgerRepository.saveAll(ls);
            return true;
        } else
            return false;

    }



}
