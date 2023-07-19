package com.nbr.trp.trptoereturn.controller;

import com.google.gson.Gson;
import com.nbr.trp.common.entity.ETinResponseModel;
import com.nbr.trp.common.service.EtinServiceImpl;
import com.nbr.trp.trptoereturn.entity.TRPEReturnOTPReponseModel;
import com.nbr.trp.trptoereturn.entity.TRPEReturnOTPRequestModel;
import com.nbr.trp.trptoereturn.entity.TRPEReturnOTPValidateModel;
import com.nbr.trp.trptoereturn.entity.TRPEReturnOTPValidatedResponse;
import com.nbr.trp.trptoereturn.service.TRPEReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/trpereturn")
public class TRPEReturnController {

    @Autowired
    TRPEReturnService trpeReturnService;

    @PostMapping("")
    public ResponseEntity<TRPEReturnOTPReponseModel> getOTPMsg(@RequestBody TRPEReturnOTPRequestModel val) {
        TRPEReturnOTPReponseModel response = trpeReturnService.getEReturnResponse(val);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/otp")
    public ResponseEntity<?> ValidateOTP(@RequestBody TRPEReturnOTPValidateModel val) {
        try{
            TRPEReturnOTPValidatedResponse response = trpeReturnService.validateOTP(val);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
//            Map<String, String> exc_map = new HashMap<String, String>();
//            exc_map.put("message", e.toString());
//            exc_map.put("stacktrace", getStackTrace(e));
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());

        }

    }
//    public static String getStackTrace(final Throwable throwable) {
//        final StringWriter sw = new StringWriter();
//        final PrintWriter pw = new PrintWriter(sw, true);
//        throwable.printStackTrace(pw);
//        return sw.getBuffer().toString();
//    }
}
