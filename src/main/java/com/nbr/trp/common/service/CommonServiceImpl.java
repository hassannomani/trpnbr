package com.nbr.trp.common.service;

import com.nbr.trp.common.entity.*;
import com.nbr.trp.common.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonServiceImpl implements CommonService{

    @Autowired
    DivisionRepository divisionRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    ThanaRepository thanaRepository;

    @Autowired
    BankRepository bankRepository;

    @Autowired
    BankNameRepository bankNameRepository;

    @Override
    public List<Division> getAllDivision() {
        List<Division> dv = divisionRepository.findAll();
        return dv;
    }

    @Override
    public List<District> getAllDistrict() {
        List<District> ds = districtRepository.findAll();
        return ds;
    }

    public List<Thana> getAllThana() {
        List<Thana> th = thanaRepository.findAll();
        return th;
    }

    public List<BankName> getAllBank() {

        List<BankName> th = bankNameRepository.findAll();
        return th;
    }

}
