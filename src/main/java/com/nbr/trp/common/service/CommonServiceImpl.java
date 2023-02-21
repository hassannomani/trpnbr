package com.nbr.trp.common.service;

import com.nbr.trp.common.entity.District;
import com.nbr.trp.common.entity.Division;
import com.nbr.trp.common.entity.Thana;
import com.nbr.trp.common.repository.DistrictRepository;
import com.nbr.trp.common.repository.DivisionRepository;
import com.nbr.trp.common.repository.ThanaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonServiceImpl implements CommonService{

    @Autowired
    DivisionRepository divisionRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    ThanaRepository thanaRepository;

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
}
