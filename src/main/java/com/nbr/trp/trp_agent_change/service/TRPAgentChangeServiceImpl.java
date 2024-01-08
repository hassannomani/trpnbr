package com.nbr.trp.trp_agent_change.service;

import com.nbr.trp.trp_agent_change.entity.TRPAgentChange;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TRPAgentChangeServiceImpl implements TRPAgentChangeService{

    @Override
    public List<TRPAgentChange> getLists(String requestedBy) {
        return null;
    }

    @Override
    public List<TRPAgentChange> getAll() {
        return null;
    }

    @Override
    public Boolean saveNewRequest(TRPAgentChange req) {
        return null;
    }

    @Override
    public Boolean updateRequest(TRPAgentChange req, String id) {
        return null;
    }
}
