package com.nbr.trp.common.service;

import com.nbr.trp.common.entity.BankInformationDetails;
import com.nbr.trp.user.entity.User;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankInformationDetailsService {

    public BankInformationDetails saveBank(BankInformationDetails bankInformationDetails);
    public BankInformationDetails findByUuid(String uuid);


}
