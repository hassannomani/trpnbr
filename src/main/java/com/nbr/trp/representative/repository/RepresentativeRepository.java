package com.nbr.trp.representative.repository;

import com.nbr.trp.agent.entity.Agent;
import com.nbr.trp.representative.entity.Representative;
import com.nbr.trp.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
    List<Representative> findByAgentId(String agentId);

    @Query(value = "select * from users, agent where agent.tin=users.username",
            nativeQuery = true)
    List<Representative> findAll();




}
