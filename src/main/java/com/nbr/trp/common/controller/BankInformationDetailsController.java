package com.nbr.trp.common.controller;

import com.nbr.trp.common.entity.BankInformationDetails;
import com.nbr.trp.common.service.BankInformationDetailsService;
import com.nbr.trp.common.service.CommonService;
import com.nbr.trp.log.LoggerController;
import jakarta.servlet.http.HttpServletRequest;
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
    CommonService commonService;

    @Autowired
    LoggerController loggerController;

    @Autowired
    public BankInformationDetailsController(BankInformationDetailsService bankInformationDetails){
        this.bankInformationDetailsService = bankInformationDetails;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add")
    public ResponseEntity<?> addBank(HttpServletRequest request, @RequestBody BankInformationDetails bankInformationDetails) {
        String ip = commonService.getIPAddress(request);
        try{
            BankInformationDetails address = bankInformationDetailsService.saveBank(bankInformationDetails);
            loggerController.InfoSave("Bank Information",ip);

            return new ResponseEntity<>(address, HttpStatus.CREATED);

        }catch(Exception e){
            loggerController.ErrorHandler(e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
