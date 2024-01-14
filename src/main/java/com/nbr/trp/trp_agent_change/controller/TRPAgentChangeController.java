package com.nbr.trp.trp_agent_change.controller;

import com.nbr.trp.common.service.CommonService;
import com.nbr.trp.log.LoggerController;
import com.nbr.trp.representative.entity.AdminTRPTransferView;
import com.nbr.trp.representative.entity.Representative;
import com.nbr.trp.trp_agent_change.entity.TRPAgentChange;
import com.nbr.trp.trp_agent_change.service.TRPAgentChangeService;
import com.nbr.trp.user.response.MessageResponse;
import com.nbr.trp.user.service.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/all")
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

    @GetMapping("/approve/{id}")
    public ResponseEntity<?> ApproveReq(HttpServletRequest request,@PathVariable String id) {
        try {
            String ip = commonService.getIPAddress(request);
            UserDetailsImpl userDetails = commonService.getDetails();
            Boolean bool = trpAgentChangeService.updateRequest(id,"1");
            loggerController.TRPRequestApprove(id,ip);
            return new ResponseEntity<>(bool, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/reject/{id}")
    public ResponseEntity<?> RejectReq(HttpServletRequest request,@PathVariable String id) {
        try {
            String ip = commonService.getIPAddress(request);
            UserDetailsImpl userDetails = commonService.getDetails();
            Boolean bool = trpAgentChangeService.updateRequest(id,"0");
            loggerController.TRPRequestApprove(id,ip);
            return new ResponseEntity<>(bool, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


}
