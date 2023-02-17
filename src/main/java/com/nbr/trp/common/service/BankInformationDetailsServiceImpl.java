package com.nbr.trp.common.service;

import com.nbr.trp.common.entity.BankInformationDetails;
import com.nbr.trp.common.repository.BankInformationDetailsRepository;
import com.nbr.trp.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BankInformationDetailsServiceImpl implements BankInformationDetailsService{

    @Autowired
    BankInformationDetailsRepository bankInformationDetailsRepository;

    @Override
    public BankInformationDetails saveBank(BankInformationDetails bankInformationDetails) {

       BankInformationDetails bankInformationDetails1 = bankInformationDetailsRepository.save(bankInformationDetails);
       return bankInformationDetails1;
    }

    @Override
    public BankInformationDetails findByUuid(String uuid) {

       BankInformationDetails informationDetails = bankInformationDetailsRepository.findByUuid(uuid);
       return  informationDetails;
    }
}
