package com.nbr.trp.commission.controller;

import com.nbr.trp.commission.entity.Commission;
import com.nbr.trp.commission.entity.CommissionBillView;
import com.nbr.trp.commission.request.ValidateRequest;
import com.nbr.trp.commission.service.CommissionService;
import com.nbr.trp.ledger.entity.Ledger;
import com.nbr.trp.user.entity.User;
import com.nbr.trp.user.response.MessageResponse;
import com.nbr.trp.user.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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



    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/validate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> validateBills(@RequestBody ValidateRequest[] valid_check){
        try{
            HashMap<String, String> ldgs = commissionService.valiDateCommission(valid_check);
            return ResponseEntity.ok(ldgs);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> approveBills(@RequestBody ValidateRequest[] approve_check){
        System.out.println("reached");
        try{
            Boolean bool = commissionService.approveBills(approve_check);
            return ResponseEntity.ok(bool);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/reject")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> rejectBills(@RequestBody ValidateRequest[] approve_check){
        System.out.println("reached");
        try{
            Boolean bool = commissionService.rejectBills(approve_check);
            return ResponseEntity.ok(bool);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/pending_bill")
    public ResponseEntity<?> getPendingBills(){
        try{
            UserDetailsImpl userDetails1 = getDetails();
            String tin = userDetails1.getUsername();

            List<CommissionBillView> ldgs = commissionService.getPendingBill(tin);
            return ResponseEntity.ok(ldgs);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/rejected_bill")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getRejectedBills(){
        try{

            UserDetailsImpl userDetails1 = getDetails();
            String tin = userDetails1.getUsername();

            List<CommissionBillView> ldgs = commissionService.getRejectedBill(tin);
            return ResponseEntity.ok(ldgs);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/approved_bill")
    public ResponseEntity<?> getapprovedBills(){
        try{
            UserDetailsImpl userDetails1 = getDetails();
            String tin = userDetails1.getUsername();

            List<CommissionBillView> ldgs = commissionService.getApprovedBill(tin);
            return ResponseEntity.ok(ldgs);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    private UserDetailsImpl getDetails(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return (UserDetailsImpl) authentication.getPrincipal();
    }



}
