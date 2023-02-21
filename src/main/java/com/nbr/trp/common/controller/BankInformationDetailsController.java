package com.nbr.trp.common.controller;

import com.nbr.trp.common.entity.BankInformationDetails;
import com.nbr.trp.common.service.BankInformationDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/bank")
public class BankInformationDetailsController {

    @Autowired
    BankInformationDetailsService bankInformationDetailsService;


    @Autowired
    public BankInformationDetailsController(BankInformationDetailsService bankInformationDetails){
        this.bankInformationDetailsService = bankInformationDetails;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add")
    public ResponseEntity<?> addBank(@RequestBody BankInformationDetails bankInformationDetails) {
        try{
            BankInformationDetails address = bankInformationDetailsService.saveBank(bankInformationDetails);
            return new ResponseEntity<>(address, HttpStatus.CREATED);

        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
