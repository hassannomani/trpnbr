package com.nbr.trp.common.controller;

import com.nbr.trp.common.entity.OTPResponseModel;
import com.nbr.trp.common.service.OTPService;
import com.nbr.trp.user.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "4200", maxAge = 4800)
@RestController
@RequestMapping("/api/otp")
public class OTPController {

    @Autowired
    OTPService otpService;

    @GetMapping("/{mobile}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> sendOTP(@PathVariable String mobile) {

        try{
            OTPResponseModel otp = otpService.sendOTP(mobile);
            return ResponseEntity.ok(otp);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/validate/{mobile}/{otp}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> validateOTP(@PathVariable String mobile, @PathVariable String otp) {

        try{
            Boolean otpMatch = otpService.validateOTP(mobile,otp);
            return ResponseEntity.ok(otpMatch);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }
}
