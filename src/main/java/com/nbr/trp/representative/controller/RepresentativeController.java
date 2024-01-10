package com.nbr.trp.representative.controller;

import com.nbr.trp.common.service.CommonService;
import com.nbr.trp.log.LoggerController;
import com.nbr.trp.representative.entity.Representative;
import com.nbr.trp.representative.entity.RepresentativeAgentView;
import com.nbr.trp.representative.repository.RepresentativeRepository;
import com.nbr.trp.representative.service.RepresentativeService;
import com.nbr.trp.user.repository.RoleRepository;
import com.nbr.trp.user.response.MessageResponse;
import com.nbr.trp.user.service.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/representative")
public class RepresentativeController {

    @Autowired
    RepresentativeRepository representativeRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RepresentativeService representativeService;

    @Autowired
    CommonService commonService;

    @Autowired
    LoggerController loggerController;

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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> getAll(HttpServletRequest request) {
        List<Representative> ls = representativeService.getAllRepresentatives();
        loggerController.ListGeneration("","All Representatives", "Admin",commonService.getIPAddress(request));
        return ResponseEntity.ok(ls);

    }

    @GetMapping("/{tin}")
    public ResponseEntity<?> getARepresentative(HttpServletRequest request, @PathVariable String tin){
        String ip = commonService.getIPAddress(request);
        UserDetailsImpl userDetails = commonService.getDetails();
        loggerController.TRPIndividualRetrival(userDetails.getUsername(),tin,ip);
        Optional<Representative> representative = representativeService.getUserByTin(tin);
        return ResponseEntity.ok(representative);

    }

    @GetMapping("/agent/{tin}")
    public ResponseEntity<?> getAllRepresentativeOfAnAgent(HttpServletRequest request,@PathVariable String tin){
        String ip = commonService.getIPAddress(request);
        UserDetailsImpl userDetails = commonService.getDetails();
        List<Representative> representativeList = representativeService.getAllRepresentativesOfAnAgent(tin);
        loggerController.ListGeneration(userDetails.getUsername(),"All TRP of Agent: "+tin,"",ip);
        return ResponseEntity.ok(representativeList);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/assign/{tin}/{agent}")
    public ResponseEntity<?> assignAgent(HttpServletRequest request,@PathVariable String tin, @PathVariable String agent){
        String ip = commonService.getIPAddress(request);
        try{
            loggerController.TRPAssign(tin,agent,ip);
            Representative representative1 = representativeService.assignAgent(tin, agent);
            return new ResponseEntity<>(representative1, HttpStatus.OK);

        }catch(Exception e){
            loggerController.ErrorHandler(e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('REPRESENTATIVE')")
    @GetMapping("/agentinfo/{tin}")
    public ResponseEntity<?> getAgentDetails(HttpServletRequest request, @PathVariable String tin){
        String ip = commonService.getIPAddress(request);
        UserDetailsImpl userDetails = commonService.getDetails();
        loggerController.TRPIndividualRetrival(userDetails.getUsername(),tin,ip);
        RepresentativeAgentView representative = representativeService.getAgentInfo(tin);
        return ResponseEntity.ok(representative);

    }


}
