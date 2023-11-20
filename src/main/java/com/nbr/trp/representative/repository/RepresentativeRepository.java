package com.nbr.trp.representative.repository;

import com.nbr.trp.agent.entity.Agent;
import com.nbr.trp.representative.entity.Representative;
import com.nbr.trp.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface RepresentativeRepository extends JpaRepository<Representative, String> {
    Optional<Representative> findByTinNo(String tin);

    Representative save(Representative representative);
    Optional<Representative> findByUserid(String userid);
    Boolean existsByTinNo(String tin);


    @Query(value = "select * from representative left join users on users.username=representative.tin_no where agent_id = :agentTin and status='1'",nativeQuery = true)
    List<Representative> findByAgentId(@Param("agentTin") String tin);

    @Query(value="select * from representative join users on representative.tin_no=users.username where users.status='1'", nativeQuery = true)
    List<Representative> findAll();

    @Query(value = "select * from representative where tin_no = :trpTin",nativeQuery = true)
    Representative findByTin(@Param("trpTin") String tin);




}
