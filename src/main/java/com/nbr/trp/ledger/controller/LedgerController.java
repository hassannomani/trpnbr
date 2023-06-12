package com.nbr.trp.ledger.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.nbr.trp.commission.entity.Items;
import com.nbr.trp.commission.service.ItemsService;
import com.nbr.trp.ledger.entity.Ledger;
import com.nbr.trp.ledger.service.LedgerService;
import com.nbr.trp.user.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/ledgers")
public class LedgerController {

    @Autowired
    LedgerService ledgerService;

    @Autowired
    ItemsService itemsService;

    @PostMapping("/")
    public ResponseEntity<?> saveLedger(@RequestBody Ledger ld){
        try{
            List<Ledger> ldPreli = ledgerService.getByAssmentYrAndTin(ld.getAssessmentYear(),ld.getTaxpayerId());
            System.out.println(ldPreli.size());
            if(ldPreli.size()==0){
                System.out.println("null found");
                Ledger ldg = ledgerService.saveLedger(ld);
                ledgerService.checkItems(ld);
                ledgerService.saveCommission(ldg);
                return ResponseEntity.ok(ldg);
            }else{
                System.out.println("bad request");
                return ResponseEntity.badRequest().body(new MessageResponse("Duplicate entry not allowed"));
            }

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

    @GetMapping("/representative/{id}")
    public ResponseEntity<?> getLedgersOfARepresentative(@PathVariable String id){
        try{
            List<Ledger> ldgs = ledgerService.getLedgersOfARepresentative(id);
            return ResponseEntity.ok(ldgs);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<?> getLedgersOfAdmin(){
        try{
            List<Ledger> ldgs = ledgerService.getLedgersOfAdmin();
            return ResponseEntity.ok(ldgs);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/range/{start}/{end}")

    public ResponseEntity<?> getLedgersRange(@PathVariable String start, @PathVariable String end){
        try{

            List<Ledger> ldglist = ledgerService.getLedgerWithinRange(start,end);
            return ResponseEntity.ok(ldglist);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }
    @GetMapping("/range-representative/{representativeId}/{start}/{end}")

    public ResponseEntity<?> getLedgersRepresentativeRange(@PathVariable String representativeId, @PathVariable String start, @PathVariable String end){

        try{

            List<Ledger> ldglist = ledgerService.getLedgersOfARepresentativeRange(representativeId,start,end);
            return ResponseEntity.ok(ldglist);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/range-agent/{agentId}/{start}/{end}")

    public ResponseEntity<?> getLedgersAgentRange(@PathVariable String agentId, @PathVariable String start, @PathVariable String end){

        try{
            List<Ledger> ldglist = ledgerService.getLedgersOfAnAgentRange(agentId,start,end);
            return ResponseEntity.ok(ldglist);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLedgerById(@PathVariable String id){

        try{
            Ledger ldg = ledgerService.getByLid(id);
            return ResponseEntity.ok(ldg);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }



}
