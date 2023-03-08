package com.nbr.trp.common.repository;

import com.nbr.trp.common.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface BankRepository extends JpaRepository<Bank, String> {

//    @Query(value = "select distinct p.bank_id, p.bank_name, p.bank_code,p.branch_code from banks p",
//            nativeQuery = true)
    List<Bank> findAll();

}
