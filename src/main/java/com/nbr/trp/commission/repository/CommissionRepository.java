package com.nbr.trp.commission.repository;

import com.nbr.trp.commission.entity.Commission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CommissionRepository extends JpaRepository<Commission, String> {


    public Commission findByCreationNo(String id);

    public Commission save(Commission commission);

    public List<Commission> findByDebitCode(String id);

    public List<Commission> findByCreditCode(String id);

}