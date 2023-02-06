package com.nbr.trp.agent.service;

import com.nbr.trp.agent.entity.Agent;
import com.nbr.trp.agent.repository.AgentRepository;
import com.nbr.trp.user.entity.ERole;
import com.nbr.trp.user.entity.Role;
import com.nbr.trp.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AgentServiceImpl implements AgentService{

    @Autowired
    AgentRepository agentRepository;

    @Override
    public Agent saveAgent(Agent agent) {
        System.out.println(agent.getRegDate());
        System.out.println(agent.getName());
        Agent agent1 = new Agent(
                UUID.randomUUID().toString(),
                agent.getName(),
                agent.getUsername(),
                agent.getPhone(),
                agent.getRegistrationType(),
                agent.getRegNo(),
                agent.getRegDate(),
                agent.getContactPerson(),
                agent.getContactEmail(),
                agent.getContactNumber(),
                agent.getBusinessAddressId(),
                agent.getCurrentAddressId(),
                agent.getPermanentAddressId(),
                agent.getBankInformationId(),
                agent.getDob(),
                agent.getFatherName(),
                agent.getMotherName(),
                agent.getSpouseName(),
                agent.getMobileNo(),
                agent.getRegAssNID()
        );
        Agent ag = agentRepository.save(agent1);
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
