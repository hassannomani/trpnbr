package com.nbr.trp.common.controller;

import com.nbr.trp.common.entity.ETinAuthRequestModel;
import com.nbr.trp.common.entity.ETinResponseModel;
import com.nbr.trp.common.service.ETinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/etin")
public class ETinController {

    @Autowired
    ETinService eTinService;

    @GetMapping("/tin/{value}")
    public ETinResponseModel getTinResponse(@PathVariable String value) {
        ETinResponseModel response = eTinService.getTinResponse("374370152508");
        return response;
    }
}