package com.nbr.trp.ledger.controller;

import com.nbr.trp.ledger.entity.Ledger;
import com.nbr.trp.ledger.service.LedgerService;
import com.nbr.trp.user.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/ledgers")
public class LedgerController {

    @Autowired
    LedgerService ledgerService;

    @PostMapping("/")
    public ResponseEntity<?> saveLedger(@RequestBody Ledger ld){
        try{
            Ledger ldg = ledgerService.saveLedger(ld);
            return ResponseEntity.ok(ldg);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getLadgers(){
        try{
            List<Ledger> ldgs = ledgerService.getAll();
            return ResponseEntity.ok(ldgs);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/agent/{id}")
    public ResponseEntity<?> getLadgersOfAnAgent(@PathVariable String id){
        try{
            List<Ledger> ldgs = ledgerService.getLadgersOfAnAgent(id);
            return ResponseEntity.ok(ldgs);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

}
