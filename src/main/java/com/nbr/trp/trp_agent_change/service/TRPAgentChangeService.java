package com.nbr.trp.trp_agent_change.service;

import com.nbr.trp.ledger.entity.Ledger;
import com.nbr.trp.trp_agent_change.entity.TRPAgentChange;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TRPAgentChangeService {

    public List<TRPAgentChange> getLists (String requestedBy);

    public List<TRPAgentChange> getAll ();
    public Boolean saveNewRequest(TRPAgentChange req);
    public Boolean updateRequest(TRPAgentChange req, String id);

}