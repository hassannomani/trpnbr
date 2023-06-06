package com.nbr.trp.ledger.controller;

import com.nbr.trp.action.entity.Action;
import com.nbr.trp.ledger.entity.Metrics;
import com.nbr.trp.ledger.service.MetricsService;
import com.nbr.trp.user.response.MessageResponse;
import com.nbr.trp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/metrics")
public class MetricsController {

    @Autowired
    MetricsService metricsService;

    @Autowired
    UserService userService;


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveAction(@RequestBody Metrics metrics) {
        try{
            Metrics metrics1 = metricsService.saveMetrics(metrics);
            return ResponseEntity.ok(metrics1);
        }catch(Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

}
