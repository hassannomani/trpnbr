package com.nbr.trp.commission.service;

import com.nbr.trp.commission.entity.Commission;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommissionService {

    public Commission saveCommission(Commission commission);

    public Commission getCommissionByCommissionNo(String id) ;

    public List<Commission> getCommissionByDebitCode(String code);

    public List<Commission> getCommissionByCreditCode(String code);
}
