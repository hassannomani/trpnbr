package com.nbr.trp.ledger.repository;

import com.nbr.trp.ledger.entity.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface LedgerRepository extends JpaRepository<Ledger, String>{

    Ledger findByTaxpayerId(String id);

    Ledger save(Ledger ledger);

    Ledger findByLid(String id);

    List<Ledger> findAll();


    List<Ledger> findByAgentTin(String id);

    List<Ledger> findByRepresentativeTin(String id);

    @Query(value = "select * from ledger where created_at >= :startDate AND created_at <=:endDate",nativeQuery = true)
    List<Ledger>findAllWithinDateRange(@Param("startDate") Timestamp startDate, @Param("endDate")Timestamp endDate);

    @Query(value = "select * from ledger where created_at >= :startDate AND created_at <=:endDate AND representative_tin=:repId",nativeQuery = true)
    List<Ledger>findAllReprstvLedgerWithinRange(@Param("repId") String repId, @Param("startDate") Timestamp startDate, @Param("endDate")Timestamp endDate);

    @Query(value = "select * from ledger where created_at >= :startDate AND created_at <=:endDate AND agent_tin=:agId",nativeQuery = true)
    List<Ledger>findAllAgentLedgerWithinRange(@Param("agId") String repId, @Param("startDate") Timestamp startDate, @Param("endDate")Timestamp endDate);

    List<Ledger> findByAssessmentYearAndTaxpayerId(String year, String id);


}




