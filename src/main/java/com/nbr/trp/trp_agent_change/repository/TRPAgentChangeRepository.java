package com.nbr.trp.trp_agent_change.repository;

import com.nbr.trp.ledger.entity.Ledger;
import com.nbr.trp.representative.entity.AdminTRPTransferView;
import com.nbr.trp.trp_agent_change.entity.TRPAgentChange;
import com.nbr.trp.trp_agent_change.entity.TRPAgentChangeHistoryView;
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
            " and a.status='0' and a.requested_by_type='ROLE_REPRESENTATIVE' order by created_at desc",nativeQuery = true)
    List<AdminTRPTransferView> findAllRequestsTRP();

    List<TRPAgentChange> findByRequestedBy(String id);

    TRPAgentChange save(TRPAgentChange req);

    List<TRPAgentChange> findByStatus(String status);

    @Query(value = "select * from transfer where status='0' and requested_by_type='ROLE_AGENT' order by created_at desc",nativeQuery = true)
    List<TRPAgentChange> findAllRequestsAgent();

    TRPAgentChange findByRequestedByAndStatusAndPreviouslyAssigned(String req, String stat, String prev);

    @Query(value = "select t.transferid, t.created_at,t.decision_at,previously_assigned,requested_by,t.status,u.first_name,u.last_name from transfer t join users u on previously_assigned=username \n" +
            "where requested_by = :id and t.status='1'" +
            "union\n" +
            "select t.transferid,t.created_at,t.decision_at,previously_assigned,requested_by,t.status,u.first_name,u.last_name from transfer t join users u on \n" +
            "requested_by = username\n" +
            "where previously_assigned= :id and t.status='1'  order by created_at desc",nativeQuery = true)
    List<TRPAgentChangeHistoryView> getPreviousTRPsOfAgent(String id);


    @Query(value = "select t.transferid, t.created_at,t.decision_at,previously_assigned,requested_by,t.status,u.first_name,u.last_name from transfer t join users u on previously_assigned=username \n" +
            "where requested_by = :id and t.status='1'" +
            "union\n" +
            "select t.transferid,t.created_at,t.decision_at,previously_assigned,requested_by,t.status,u.first_name,u.last_name from transfer t join users u on \n" +
            "requested_by = username\n" +
            "where previously_assigned= :id and t.status='1'  order by created_at desc",nativeQuery = true)
    List<TRPAgentChangeHistoryView> getPreviousAgentsOfTRP(String id);

}

