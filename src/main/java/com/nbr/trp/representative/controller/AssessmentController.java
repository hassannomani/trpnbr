package com.nbr.trp.representative.controller;

import com.nbr.trp.representative.entity.AssessmentYear;
import com.nbr.trp.representative.entity.Representative;
import com.nbr.trp.representative.service.RepresentativeService;
import com.nbr.trp.representative.service.YearService;
import com.nbr.trp.user.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/year")
public class AssessmentController {

    @Autowired
    YearService yearService;

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add/{year}")
    public ResponseEntity<?> addYear(@PathVariable String year) {
        System.out.println(year);
        try{
            AssessmentYear assessmentYear = yearService.saveyear(year);
            // return ResponseEntity.ok(new MessageResponse("Representative registered successfully!"));
            return new ResponseEntity<>(assessmentYear, HttpStatus.CREATED);

        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/find/{year}")
    public ResponseEntity<?> findYear(@PathVariable String year) {

        try{
            AssessmentYear assessmentYear = yearService.saveyear(year);
            // return ResponseEntity.ok(new MessageResponse("Representative registered successfully!"));
            return new ResponseEntity<>(assessmentYear, HttpStatus.CREATED);

        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
