package com.nbr.trp.common.service;

import com.nbr.trp.common.entity.BankInformationDetails;
import com.nbr.trp.user.entity.User;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankInformationDetailsService {

    public User saveBank(User user);

    public List<BankInformationDetails> getAllBanks();

    public BankInformationDetails findByUuid(String uuid);


}
