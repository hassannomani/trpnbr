package com.nbr.trp.representative.controller;

import com.nbr.trp.agent.entity.Agent;
import com.nbr.trp.agent.repository.AgentRepository;
import com.nbr.trp.agent.service.AgentService;
import com.nbr.trp.representative.entity.Representative;
import com.nbr.trp.representative.repository.RepresentativeRepository;
import com.nbr.trp.representative.service.RepresentativeService;
import com.nbr.trp.user.repository.RoleRepository;
import com.nbr.trp.user.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/representative")
public class RepresentativeController {

    @Autowired
    RepresentativeRepository representativeRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RepresentativeService representativeService;

    @PostMapping("/add")
    public ResponseEntity<?> addRepresentative(@RequestBody Representative representative) {
        if (representativeRepository.existsByTinNo(representative.getTinNo())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: username is already taken!"));
        }

        try{
            Representative representative1 = representativeService.saveRepresentative(representative);
               // return ResponseEntity.ok(new MessageResponse("Representative registered successfully!"));
            return new ResponseEntity<>(representative1, HttpStatus.CREATED);

        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/all")
    public ResponseEntity<?> getAll() {
        List<Representative> ls = representativeService.getAllRepresentatives();
        return ResponseEntity.ok(ls);

    }

}
