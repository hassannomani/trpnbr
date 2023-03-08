package com.nbr.trp.common.repository;

import com.nbr.trp.common.entity.BankName;
import com.nbr.trp.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface BankNameRepository extends JpaRepository<BankName, String> {

    List<BankName> findAll();
}
