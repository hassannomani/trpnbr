package com.nbr.trp.trp_agent_change.repository;

import com.nbr.trp.ledger.entity.Ledger;
import com.nbr.trp.trp_agent_change.entity.TRPAgentChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface TRPAgentChangeRepository extends JpaRepository<TRPAgentChange, String> {

    TRPAgentChange findByTransferid(String id);

    List<TRPAgentChange> findAll();

    List<TRPAgentChange> findByRequestedBy(String id);

    TRPAgentChange save(TRPAgentChange req);
}
