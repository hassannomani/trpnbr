package com.nbr.trp.trp_agent_change.service;

import com.nbr.trp.ledger.entity.Ledger;
import com.nbr.trp.representative.entity.AdminTRPTransferView;
import com.nbr.trp.trp_agent_change.entity.TRPAgentChange;
import com.nbr.trp.trp_agent_change.entity.TRPAgentChangeHistoryView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TRPAgentChangeService {

    public List<TRPAgentChange> getLists (String requestedBy);

    public List<AdminTRPTransferView> getAll ();
    public Boolean saveNewRequest(TRPAgentChange req);
    public Boolean ApproveRequestAgent(String id, String agent);

    public Boolean ApproveRequestTRP(String id);

    public Boolean RejectRequest(String id);

    public List<TRPAgentChange> getAllAgent ();

    public List<TRPAgentChangeHistoryView> getPreviousTRPs (String id);

    public List<TRPAgentChangeHistoryView> getPreviousAgents (String id);




}
