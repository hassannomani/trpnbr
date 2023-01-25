package com.nbr.trp.user.controller;

import com.nbr.trp.user.response.MessageResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/test")
public class UserController {
    @GetMapping("/all")
    public MessageResponse allAccess() {
        return new MessageResponse("Public ");
    }

    @GetMapping("/employee")
    @PreAuthorize("hasRole('EMPLOYEE') ")
    public MessageResponse employeeAccess() {

        return new MessageResponse("Employee zone");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public MessageResponse adminAccess() {
        return new MessageResponse("Admin zone");
    }
}
