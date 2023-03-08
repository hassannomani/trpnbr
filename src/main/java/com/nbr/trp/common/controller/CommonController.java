package com.nbr.trp.common.controller;

import com.nbr.trp.common.entity.*;
import com.nbr.trp.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/common")
public class CommonController {

    @Autowired
    public CommonService commonService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/division")
    public ResponseEntity<?> getDiv() {
        try{
            List<Division> dv = commonService.getAllDivision();
            // return ResponseEntity.ok(new MessageResponse("Representative registered successfully!"));
            return new ResponseEntity<>(dv, HttpStatus.CREATED);

        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/district")
    public ResponseEntity<?> getDistr() {
        try{
            List<District> ds = commonService.getAllDistrict();
            // return ResponseEntity.ok(new MessageResponse("Representative registered successfully!"));
            return new ResponseEntity<>(ds, HttpStatus.CREATED);

        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/thana")
    public ResponseEntity<?> getThana() {
        try{
            List<Thana> ds = commonService.getAllThana();
            // return ResponseEntity.ok(new MessageResponse("Representative registered successfully!"));
            return new ResponseEntity<>(ds, HttpStatus.CREATED);

        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/bank")
    public ResponseEntity<?> getBank() {
        try{
            List<BankName> ds = commonService.getAllBank();
            // return ResponseEntity.ok(new MessageResponse("Representative registered successfully!"));
            return new ResponseEntity<>(ds, HttpStatus.CREATED);

        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
