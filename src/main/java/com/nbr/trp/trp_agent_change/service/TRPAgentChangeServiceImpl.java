package com.nbr.trp.trp_agent_change.service;

import com.nbr.trp.trp_agent_change.entity.TRPAgentChange;
import com.nbr.trp.trp_agent_change.repository.TRPAgentChangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TRPAgentChangeServiceImpl implements TRPAgentChangeService{

    @Autowired
    TRPAgentChangeRepository trpAgentChangeRepository;

    @Override
    public List<TRPAgentChange> getLists(String requestedBy) {
        return null;
    }

    @Override
    public List<TRPAgentChange> getAll() {
        return trpAgentChangeRepository.findByStatus("0");
    }

    @Override
    public Boolean saveNewRequest(TRPAgentChange req) {
        trpAgentChangeRepository.save(req);
        return true;
    }

    @Override
    public Boolean updateRequest(TRPAgentChange req, String id) {
        return null;
    }
}
