package com.nbr.trp.agent.repository;

import com.nbr.trp.agent.entity.Agent;
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
public interface AgentRepository extends JpaRepository<Agent, String> {

    Optional<Agent> findByTin(String tin);

    Agent save(Agent agent);

    Boolean existsByTin(String username);

    Optional<Agent> findById(String id);

    @Query(value = "select * from users, agent where agent.tin=users.username order by added_date desc",
            nativeQuery = true)
    List<Agent> findAll();

    @Query(value = "select id, tin, name from agent",nativeQuery = true)
    List<Object[]> agentFrontEnd();
}
