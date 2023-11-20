package com.nbr.trp.commission.controller;

import com.nbr.trp.commission.entity.Commission;
import com.nbr.trp.commission.entity.CommissionBillView;
import com.nbr.trp.commission.request.ValidateRequest;
import com.nbr.trp.commission.service.CommissionService;
import com.nbr.trp.common.service.CommonService;
import com.nbr.trp.ledger.entity.Ledger;
import com.nbr.trp.log.LoggerController;
import com.nbr.trp.user.entity.User;
import com.nbr.trp.user.response.MessageResponse;
import com.nbr.trp.user.service.UserDetailsImpl;
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

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/commission")
public class CommissionController {
    @Autowired
    CommissionService commissionService;

    @Autowired
    CommonService commonService;

    @Autowired
    LoggerController loggerController;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> getAllCommissions(HttpServletRequest request){
        String ip = commonService.getIPAddress(request);
        try{
            List<Commission> commissionList = commissionService.getAll();
            loggerController.ListGeneration("","All Commission","Admin",ip);
            return ResponseEntity.ok(commissionList);
        } catch(Exception e){
            loggerController.ErrorHandler(e);
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getTrpCommission(HttpServletRequest request, @PathVariable String username) {
        UserDetailsImpl userDetails1 = getDetails();
        String tin = userDetails1.getUsername();
        String ip = commonService.getIPAddress(request);

        try {
            List<Commission> commissionList = commissionService.getCommissionByCreditCode(username);
            loggerController.ListGeneration(tin,"Commission of "+username,"",ip);
            return ResponseEntity.ok(commissionList);
        } catch (Exception e) {
            loggerController.ErrorHandler(e);
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }



    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/validate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> validateBills(HttpServletRequest request, @RequestBody ValidateRequest[] valid_check){
        String ip = commonService.getIPAddress(request);
        try{
            HashMap<String, String> ldgs = commissionService.valiDateCommission(valid_check);
            loggerController.CommissionValidate(ip,ldgs.toString());
            return ResponseEntity.ok(ldgs);
        } catch(Exception e){
            loggerController.ErrorHandler(e);
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> approveBills(HttpServletRequest request,@RequestBody ValidateRequest[] approve_check){
        String ip = commonService.getIPAddress(request);
        try{
            Boolean bool = commissionService.approveBills(approve_check);
            loggerController.CommissionApprove(ip,approve_check.toString());
            return ResponseEntity.ok(bool);
        } catch(Exception e){
            loggerController.ErrorHandler(e);
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/reject")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> rejectBills(HttpServletRequest request,@RequestBody ValidateRequest[] approve_check){
        String ip = commonService.getIPAddress(request);
        try{
            Boolean bool = commissionService.rejectBills(approve_check);
            loggerController.CommissionRejection(ip,approve_check.toString());
            return ResponseEntity.ok(bool);
        } catch(Exception e){
            loggerController.ErrorHandler(e);
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/pending_bill")
    public ResponseEntity<?> getPendingBills(HttpServletRequest request){
        String ip = commonService.getIPAddress(request);
        try{
            UserDetailsImpl userDetails1 = getDetails();
            String tin = userDetails1.getUsername();

            List<CommissionBillView> ldgs = commissionService.getPendingBill(tin);
            loggerController.ListGeneration(tin,"Pending Bill ","",ip);
            return ResponseEntity.ok(ldgs);
        } catch(Exception e){
            loggerController.ErrorHandler(e);
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/rejected_bill")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getRejectedBills(HttpServletRequest request){
        String ip = commonService.getIPAddress(request);
        try{

            UserDetailsImpl userDetails1 = getDetails();
            String tin = userDetails1.getUsername();

            List<CommissionBillView> ldgs = commissionService.getRejectedBill(tin);
            loggerController.ListGeneration(tin,"Rejected Bills ","",ip);
            return ResponseEntity.ok(ldgs);
        } catch(Exception e){
            loggerController.ErrorHandler(e);
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/approved_bill")
    public ResponseEntity<?> getapprovedBills(HttpServletRequest request){
        String ip = commonService.getIPAddress(request);
        try{
            UserDetailsImpl userDetails1 = getDetails();
            String tin = userDetails1.getUsername();
            List<CommissionBillView> ldgs = commissionService.getApprovedBill(tin);
            loggerController.ListGeneration(tin,"Approved Bills ","",ip);
            return ResponseEntity.ok(ldgs);
        } catch(Exception e){
            loggerController.ErrorHandler(e);
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    private UserDetailsImpl getDetails(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return (UserDetailsImpl) authentication.getPrincipal();
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/get_applicants")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getApplicants(){
        try{
            Object[] ldgs = commissionService.get_Applicants();
            return ResponseEntity.ok(ldgs);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

}
