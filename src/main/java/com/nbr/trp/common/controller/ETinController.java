package com.nbr.trp.common.controller;

import com.nbr.trp.common.entity.ETinAuthRequestModel;
import com.nbr.trp.common.entity.ETinResponseModel;
import com.nbr.trp.common.service.BankInformationDetailsService;
import com.nbr.trp.common.service.ETinService;
import com.nbr.trp.common.service.EtinServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/etin")
public class ETinController {

    @Autowired
    EtinServiceImpl eTinService;

    @Autowired
    public ETinController(EtinServiceImpl eTinService){
        this.eTinService = eTinService;
    }

    @GetMapping("/tin/{value}")
    public ETinResponseModel getTinResponse(@PathVariable String value) {
        ETinResponseModel response = eTinService.getTinResponse("374370152508");
        return response;
    }
}