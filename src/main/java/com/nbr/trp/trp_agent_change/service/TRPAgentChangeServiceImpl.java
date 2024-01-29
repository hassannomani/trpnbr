package com.nbr.trp.trp_agent_change.service;

import com.nbr.trp.representative.entity.AdminTRPTransferView;
import com.nbr.trp.representative.entity.Representative;
import com.nbr.trp.representative.repository.RepresentativeRepository;
import com.nbr.trp.trp_agent_change.entity.TRPAgentChange;
import com.nbr.trp.trp_agent_change.entity.TRPAgentChangeHistoryView;
import com.nbr.trp.trp_agent_change.repository.TRPAgentChangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Service
public class TRPAgentChangeServiceImpl implements TRPAgentChangeService{

    @Autowired
    TRPAgentChangeRepository trpAgentChangeRepository;

    @Autowired
    RepresentativeRepository representativeRepository;

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
        TRPAgentChange trpAgentChange = trpAgentChangeRepository.findByRequestedByAndStatusAndPreviouslyAssigned(req.getRequestedBy(),"0",req.getPreviouslyAssigned());
        if(trpAgentChange!=null)
            return false;
        else{
            trpAgentChangeRepository.save(req);
            return true;
        }
    }

    @Override
    public Boolean ApproveRequestTRP(String id) {
        TRPAgentChange req = trpAgentChangeRepository.findByTransferid(id);
        req.setDecisionAt(new Date().toString());
        req.setStatus("1");
        Representative rep = representativeRepository.findByTin(req.getRequestedBy());
        rep.setAgentId(req.getRequestFor());
        representativeRepository.save(rep);
        trpAgentChangeRepository.save(req);
        return true;
    }

    @Override
    public List<TRPAgentChange> getAllAgent (){
        return trpAgentChangeRepository.findAllRequestsAgent();
    }

    @Override
    public Boolean ApproveRequestAgent(String id,String agent) {
        TRPAgentChange req = trpAgentChangeRepository.findByTransferid(id);
        req.setDecisionAt(new Date().toString());
        req.setStatus("1");
        req.setRequestFor(agent);
        Representative rep = representativeRepository.findByTin(req.getPreviouslyAssigned());
        rep.setAgentId(agent);
        representativeRepository.save(rep);
        trpAgentChangeRepository.save(req);
        return true;
    }

    @Override
    public Boolean RejectRequest(String id) {
        TRPAgentChange req = trpAgentChangeRepository.findByTransferid(id);
        req.setDecisionAt(new Date().toString());
        req.setStatus("-1");
        trpAgentChangeRepository.save(req);
        return true;
    }

    @Override
    public List<TRPAgentChangeHistoryView> getPreviousTRPs (String id){
        return trpAgentChangeRepository.getPreviousTRPsOfAgent(id);
    }

    @Override
    public List<TRPAgentChangeHistoryView> getPreviousAgents (String id){
        return trpAgentChangeRepository.getPreviousTRPsOfAgent(id);
    }

}
