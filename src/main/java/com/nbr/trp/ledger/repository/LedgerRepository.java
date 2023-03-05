package com.nbr.trp.ledger.repository;

import com.nbr.trp.ledger.entity.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface LedgerRepository extends JpaRepository<Ledger, String>{

    Ledger findByTaxpayerId(String id);

    Ledger save(Ledger ledger);

    Optional<Ledger> findByLid(String id);

    List<Ledger> findAll();


    List<Ledger> findByAgentId(String id);

    List<Ledger> findByRepresentativeId(String id);

//    @Query(value = "select * from users, agent where agent.tin=users.username",
//            nativeQuery = true)
//    List<Ledger> findAgentAll(String agent);
//
//    List<Ledger> findRepresentativeAll(String representative);


}




