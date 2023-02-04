package com.nbr.trp.agent.service;

import com.nbr.trp.agent.entity.Agent;
import com.nbr.trp.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AgentService {
    public Agent saveAgent(Agent agent);

    public List<Agent> getAllAgents();

    public Optional<Agent> getAgentByUsername(String username);

    public Optional<Agent> getAgentByUuid(String uuid);

}
