package com.nbr.trp.common.repository;

import com.nbr.trp.common.entity.Address;
import com.nbr.trp.common.entity.BankInformationDetails;
import com.nbr.trp.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface BankInformationDetailsRepository  extends JpaRepository<BankInformationDetails, String> {

    public BankInformationDetails save(BankInformationDetails bankInformationDetails);

    public BankInformationDetails findByUuid(String uuid);
}
