package com.nbr.trp.commission.service;

import com.nbr.trp.commission.entity.Commission;
import com.nbr.trp.commission.repository.CommissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommissionServiceImpl implements CommissionService {

    @Autowired
    CommissionRepository commissionRepository;


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
        return null;
    }
}

