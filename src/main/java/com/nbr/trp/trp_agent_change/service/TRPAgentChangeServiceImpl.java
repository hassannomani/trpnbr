package com.nbr.trp.trp_agent_change.service;

import com.nbr.trp.representative.entity.AdminTRPTransferView;
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
    public List<AdminTRPTransferView> getAll() {
        return trpAgentChangeRepository.findAllRequestsTRP();
    }

    @Override
    public Boolean saveNewRequest(TRPAgentChange req) {
        trpAgentChangeRepository.save(req);
        return true;
    }

    @Override
    public Boolean updateRequest(String id, String approve) {
        TRPAgentChange req = trpAgentChangeRepository.findByTransferid(id);
        if(approve.equals("0"))
            req.setStatus("-1");
        else
            req.setStatus("1");
        trpAgentChangeRepository.save(req);
        return true;
    }
}
