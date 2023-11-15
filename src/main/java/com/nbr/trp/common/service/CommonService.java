package com.nbr.trp.common.service;

import com.nbr.trp.common.entity.*;
import com.nbr.trp.user.service.UserDetailsImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommonService {
    List<Division> getAllDivision();

    List<District> getAllDistrict();

    List<Thana> getAllThana();

    List<BankName> getAllBank();
    List<BankDistrict> getAllBankDist();
    List<Bank> getAllBankBranches(String bank, String district);

    List<CityCorporation> getAllCityCorporation();

    UserDetailsImpl getDetails();


}
