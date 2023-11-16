package com.nbr.trp.ledger.controller;

import com.nbr.trp.ledger.entity.Ledger;
import com.nbr.trp.ledger.entity.LedgerAdminView;
import com.nbr.trp.ledger.request.BillRequest;
import com.nbr.trp.ledger.service.BillService;
import com.nbr.trp.ledger.service.LedgerService;
import com.nbr.trp.log.LoggerController;
import com.nbr.trp.user.filter.AuthTokenFilter;
import com.nbr.trp.user.response.MessageResponse;
import com.nbr.trp.user.service.UserDetailsImpl;
import com.nbr.trp.user.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/bill")
public class BillController {

    @Autowired
    LedgerService ledgerService;

    @Autowired
    BillService billService;

    @Autowired
    UserDetailsImpl userDetails;

    @Autowired
    LoggerController loggerController;

    @GetMapping("/billable-agt/{agent}")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<?> getAgentBill(@PathVariable String agent){
        try{
            List<Ledger> ldgs = billService.getAgentBillableList(agent);
            loggerController.BillCheck("AGENT",agent);
            return ResponseEntity.ok(ldgs);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/billable-trp/{trp}")
    @PreAuthorize("hasRole('REPRESENTATIVE')")
    public ResponseEntity<?> getTRPBill(@PathVariable String trp){
        try{
            List<Ledger> ldgs = billService.getTRPBillableList(trp);
            loggerController.BillCheck("REPRESENTATIVE",trp);
            return ResponseEntity.ok(ldgs);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @PostMapping("/billSubmit")
    @PreAuthorize("hasAnyRole('AGENT','REPRESENTATIVE')")
    public ResponseEntity<?> billSubmission(HttpServletRequest request, @RequestBody BillRequest bills){
        try{
            //List<Ledger> ldgs = billService.getTRPBillableList(trp);
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = context.getAuthentication();
            UserDetailsImpl userDetails1 = (UserDetailsImpl) authentication.getPrincipal();
            String tin = userDetails1.getUsername();
            String role = bills.getRole();
            String[] ids = bills.getId();
            Boolean bool = billService.saveBill(role, ids, tin);
            HashMap<String, String> map = new HashMap<>();
            map.put("status", "200");
            map.put("success", "true");
            loggerController.BillSubmit(tin,role);
            return ResponseEntity.ok(map);
        } catch(Exception e){
            loggerController.ErrorHandler(e);
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }





}
