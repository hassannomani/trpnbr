package com.nbr.trp.commission.controller;

import com.nbr.trp.commission.entity.Commission;
import com.nbr.trp.commission.service.CommissionService;
import com.nbr.trp.ledger.entity.Ledger;
import com.nbr.trp.user.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/commission")
public class CommissionController {
    @Autowired
    CommissionService commissionService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllCommissions(){
        try{
            List<Commission> commissionList = commissionService.getAll();
            return ResponseEntity.ok(commissionList);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getTrpCommission(@PathVariable String username) {
        try {
            List<Commission> commissionList = commissionService.getCommissionByCreditCode(username);
            return ResponseEntity.ok(commissionList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }


}
