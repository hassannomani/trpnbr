package com.nbr.trp.commission.service;

import com.nbr.trp.commission.entity.Commission;
import com.nbr.trp.commission.entity.CommissionBillView;
import com.nbr.trp.commission.request.ValidateRequest;
import com.nbr.trp.ledger.entity.Ledger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface CommissionService {

    public Commission saveCommission(Commission commission);

    public Commission getCommissionByCommissionNo(String id) ;

    public List<Commission> getCommissionByDebitCode(String code);

    public List<Commission> getCommissionByCreditCode(String code);

    public HashMap<String, String> calculateCommission(Ledger ld);

    public List<Commission> getAll();

    public Boolean SaveBulkCommission(String role, String tin, String[] ids);

    public List<CommissionBillView> getPendingBill(String tin);

    public HashMap<String, String> valiDateCommission(ValidateRequest[] reqs);

    public Boolean approveBills(ValidateRequest[] reqs);

    public Boolean rejectBills(ValidateRequest[] reqs);

    public List<CommissionBillView> getRejectedBill(String tin);

    public List<CommissionBillView> getApprovedBill(String tin);

    public Object[] get_Applicants();

}
