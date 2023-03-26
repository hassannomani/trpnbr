package com.nbr.trp.common.repository;

import com.nbr.trp.common.entity.BankDistrict;
import com.nbr.trp.common.entity.BankName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@EnableJpaRepositories
public interface BankDistRepository extends JpaRepository<BankDistrict, String> {
    List<BankDistrict> findAll();
}