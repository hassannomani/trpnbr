package com.nbr.trp.ledger.controller;

import com.nbr.trp.ledger.service.MetricsService;
import com.nbr.trp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/metrics")
public class MetricsController {

    @Autowired
    MetricsService metricsService;

    @Autowired
    UserService userService;


}
