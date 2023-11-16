package com.nbr.trp.common.service;

import com.nbr.trp.common.entity.*;
import com.nbr.trp.common.repository.*;
import com.nbr.trp.user.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    BankDistRepository bankDistRepository;

    @Autowired
    CityCorporationRepository cityCorporationRepository;

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

    public List<BankDistrict> getAllBankDist() {

        List<BankDistrict> bankDistricts = bankDistRepository.findAll();
        return bankDistricts;
    }

    public List<Bank> getAllBankBranches(String name, String district) {

        List<Bank> bankDistricts = bankRepository.findByBankNameAndDistName(name, district);
        return bankDistricts;
    }

    public List<CityCorporation> getAllCityCorporation(){
        List<CityCorporation> cityCorporations = cityCorporationRepository.findAll();
        return cityCorporations;
    }

    public UserDetailsImpl getDetails(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object temp =  authentication.getPrincipal();
        String type = temp.getClass().getName();
        if(type.equals("java.lang.String")){
            return null;
        }else{
            UserDetailsImpl userDetails = (UserDetailsImpl) temp;
            return userDetails;
        }
         //java.lang.String
    }

}
