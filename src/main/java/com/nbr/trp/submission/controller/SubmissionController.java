package com.nbr.trp.submission.controller;

import com.nbr.trp.representative.entity.Representative;
import com.nbr.trp.representative.repository.RepresentativeRepository;
import com.nbr.trp.representative.service.RepresentativeService;
import com.nbr.trp.submission.entity.Submission;
import com.nbr.trp.submission.repository.SubmissionRepository;
import com.nbr.trp.submission.service.SubmissionService;
import com.nbr.trp.user.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/submission")
public class SubmissionController {

    @Autowired
    SubmissionRepository submissionRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    SubmissionService submissionService;

    @PostMapping("/")
    public ResponseEntity<?> addRepresentative(@RequestBody Submission submission) {
        try{
            Submission sub = submissionService.saveSubmission(submission);
            // return ResponseEntity.ok(new MessageResponse("Representative registered successfully!"));
            return new ResponseEntity<>(sub, HttpStatus.CREATED);

        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        List<Submission> ls = submissionService.getAllSubmission();
        return ResponseEntity.ok(ls);
    }

    @GetMapping("/{sid}")
    public ResponseEntity<?> getASubmission(@PathVariable String tin) {
        Submission sb = submissionService.getSubmissionByTin(tin);
        return ResponseEntity.ok(sb);
    }


}
