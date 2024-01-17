package com.nbr.trp.trp_agent_change.repository;

import com.nbr.trp.ledger.entity.Ledger;
import com.nbr.trp.representative.entity.AdminTRPTransferView;
import com.nbr.trp.trp_agent_change.entity.TRPAgentChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface TRPAgentChangeRepository extends JpaRepository<TRPAgentChange, String> {

    TRPAgentChange findByTransferid(String id);

    @Query(value = "select transferid, reason, a.created_at, u.first_name, u.last_name, j.first_name as ag_first_name,j.last_name as ag_last_name, k.first_name as prev_first_name, k.last_name as prev_last_name from \n" +
            "transfer a,users u, users j, users k\n" +
            "where a.requested_by = u.username\n" +
            "and a.request_for=j.username\n" +
            "and a.previously_assigned=k.username" +
            " and a.status='0' and a.requested_by_type='ROLE_REPRESENTATIVE'",nativeQuery = true)
    List<AdminTRPTransferView> findAllRequestsTRP();

    List<TRPAgentChange> findByRequestedBy(String id);

    TRPAgentChange save(TRPAgentChange req);

    List<TRPAgentChange> findByStatus(String status);

    @Query(value = "select * from transfer where status='0' and requested_by_type='ROLE_AGENT'",nativeQuery = true)
    List<TRPAgentChange> findAllRequestsAgent();
}
