package com.nbr.trp.trp_agent_change.controller;

import com.nbr.trp.common.service.CommonService;
import com.nbr.trp.log.LoggerController;
import com.nbr.trp.representative.entity.AdminTRPTransferView;
import com.nbr.trp.representative.entity.Representative;
import com.nbr.trp.trp_agent_change.entity.TRPAgentChange;
import com.nbr.trp.trp_agent_change.entity.TRPAgentChangeHistoryView;
import com.nbr.trp.trp_agent_change.service.TRPAgentChangeService;
import com.nbr.trp.user.response.MessageResponse;
import com.nbr.trp.user.service.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/v1/changerequest")
public class TRPAgentChangeController {

    @Autowired
    CommonService commonService;

    @Autowired
    LoggerController loggerController;

    @Autowired
    TRPAgentChangeService trpAgentChangeService;

    @PostMapping("/save")
    public ResponseEntity<?> saveRequest(HttpServletRequest request,@RequestBody TRPAgentChange trpAgentChange) {
        try {
            String ip = commonService.getIPAddress(request);
            UserDetailsImpl userDetails = commonService.getDetails();
            Boolean bool = trpAgentChangeService.saveNewRequest(trpAgentChange);
            loggerController.AgentChange(userDetails.getUsername(),ip);
            return new ResponseEntity<>(bool, HttpStatus.CREATED);
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all-trp")
    public ResponseEntity<?> GetAllRequest(HttpServletRequest request) {
        try {
            String ip = commonService.getIPAddress(request);
            UserDetailsImpl userDetails = commonService.getDetails();
            List<AdminTRPTransferView> trpAgentChangeList = trpAgentChangeService.getAll();
            loggerController.AgentChange(userDetails.getUsername(),ip);
            return new ResponseEntity<>(trpAgentChangeList, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/approve/{id}")
    public ResponseEntity<?> ApproveReqTRP(HttpServletRequest request,@PathVariable String id) {
        try {
            String ip = commonService.getIPAddress(request);
            UserDetailsImpl userDetails = commonService.getDetails();
            Boolean bool = trpAgentChangeService.ApproveRequestTRP(id);
            loggerController.TRPRequestApprove(id,ip);
            return new ResponseEntity<>(bool, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/reject/{id}")
    public ResponseEntity<?> RejectReq(HttpServletRequest request, @PathVariable String id) {
        try {
            String ip = commonService.getIPAddress(request);
            UserDetailsImpl userDetails = commonService.getDetails();
            Boolean bool = trpAgentChangeService.RejectRequest(id);
            loggerController.TRPRequestApprove(id,ip);
            return new ResponseEntity<>(bool, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all-agent")
    public ResponseEntity<?> GetAllAgentRequest(HttpServletRequest request) {
        try {
            String ip = commonService.getIPAddress(request);
            UserDetailsImpl userDetails = commonService.getDetails();
            List<TRPAgentChange> trpAgentChangeList = trpAgentChangeService.getAllAgent();
            loggerController.AgentChange(userDetails.getUsername(),ip);
            return new ResponseEntity<>(trpAgentChangeList, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/approve-agent-req/{transferid}/{agentid}")
    public ResponseEntity<?> ApproveReqAgent(HttpServletRequest request,@PathVariable String transferid, @PathVariable String agentid) {
        try {
            String ip = commonService.getIPAddress(request);
            UserDetailsImpl userDetails = commonService.getDetails();
            Boolean bool = trpAgentChangeService.ApproveRequestAgent(transferid,agentid);
            loggerController.TRPRequestApprove(transferid,ip);
            return new ResponseEntity<>(bool, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    @GetMapping("/previous-trp-of-agent/{agentId}")
    public ResponseEntity<?> GetAllPreviousTRPOfAgent(HttpServletRequest request, @PathVariable String agentId) {
        try {
            String ip = commonService.getIPAddress(request);
            UserDetailsImpl userDetails = commonService.getDetails();
            List<TRPAgentChangeHistoryView> trpAgentChangeList = trpAgentChangeService.getPreviousTRPs(agentId);
            loggerController.AgentChange(userDetails.getUsername(),ip);
            return new ResponseEntity<>(trpAgentChangeList, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/previous-agents-of-trp/{trpId}")
    public ResponseEntity<?> GetAllPreviousAgentOfTRP(HttpServletRequest request, @PathVariable String trpId) {
        try {
            String ip = commonService.getIPAddress(request);
            UserDetailsImpl userDetails = commonService.getDetails();
            List<TRPAgentChangeHistoryView> trpAgentChangeList = trpAgentChangeService.getPreviousAgents(trpId);
            loggerController.AgentChange(userDetails.getUsername(),ip);
            return new ResponseEntity<>(trpAgentChangeList, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }



}
