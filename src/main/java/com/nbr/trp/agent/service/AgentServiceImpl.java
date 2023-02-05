package com.nbr.trp.agent.service;

import com.nbr.trp.agent.entity.Agent;
import com.nbr.trp.agent.repository.AgentRepository;
import com.nbr.trp.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgentServiceImpl implements AgentService{

    @Autowired
    AgentRepository agentRepository;

    @Override
    public Agent saveAgent(Agent agent) {
        Agent ag = agentRepository.save(agent);
        return ag;
    }

    @Override
    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    @Override
    public Optional<Agent> getAgentByUsername(String username) {
        return Optional.ofNullable(agentRepository.findByUsername(username).orElse(null));
    }

    @Override
    public Optional<Agent> getAgentByUuid(String uuid) {
        return Optional.ofNullable(agentRepository.findByUuid(uuid).orElse(null));
    }
}
