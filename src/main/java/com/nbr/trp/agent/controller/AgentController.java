package com.nbr.trp.agent.controller;

import com.nbr.trp.agent.entity.Agent;
import com.nbr.trp.agent.repository.AgentRepository;
import com.nbr.trp.agent.service.AgentService;
import com.nbr.trp.user.repository.RoleRepository;
import com.nbr.trp.user.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/agent")
public class AgentController {


    @Autowired
    AgentRepository agentRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AgentService agentService;

    @PostMapping("/add")
    public ResponseEntity<?> addAgent(@RequestBody Agent agent) {
        System.out.println(agent);
        if (agentRepository.existsByTin(agent.getTin())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: username is already taken!"));
        }

        Agent saveAgent = agentService.saveAgent(agent);
        if(saveAgent.getId()!=null)
            return ResponseEntity.ok(new MessageResponse("Employee registered successfully!"));
        else
            return ResponseEntity.internalServerError().body(new MessageResponse("Failed!"));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        List<Agent> ls = agentService.getAllAgents();
        return ResponseEntity.ok(ls);
    }

    @GetMapping("/{tin}")
    public ResponseEntity<?> getAnAgent(@PathVariable String tin) {
        try{
            Optional<Agent> ag = agentService.getAgentByTin(tin);
            return ResponseEntity.ok(ag);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(new MessageResponse("Failed!"));
        }

    }


}
