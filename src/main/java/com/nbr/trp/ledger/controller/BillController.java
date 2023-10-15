package com.nbr.trp.ledger.controller;

import com.nbr.trp.ledger.entity.Ledger;
import com.nbr.trp.ledger.entity.LedgerAdminView;
import com.nbr.trp.ledger.service.BillService;
import com.nbr.trp.ledger.service.LedgerService;
import com.nbr.trp.user.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/bill")
public class BillController {

    @Autowired
    LedgerService ledgerService;

    @Autowired
    BillService billService;

    @GetMapping("/billable-agt/{agent}")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<?> getAgentBill(@PathVariable String agent){
        try{
            List<Ledger> ldgs = billService.getAgentBillableList(agent);
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
            return ResponseEntity.ok(ldgs);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

}
