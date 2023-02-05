package com.nbr.trp.agent.repository;

import com.nbr.trp.agent.entity.Agent;
import com.nbr.trp.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface AgentRepository extends JpaRepository<Agent, String> {

    Optional<Agent> findByUsername(String username);

    Agent save(Agent agent);

    Optional<Agent> findByUuid(String uuid);
}
