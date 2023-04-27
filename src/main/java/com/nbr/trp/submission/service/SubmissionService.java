package com.nbr.trp.submission.service;

import com.nbr.trp.submission.entity.Submission;
import com.nbr.trp.submission.exception.SubmissionNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubmissionService {
    public Submission getSubmissionByRuid(String id);

    public Submission getSubmissionByTin(String tin);

    public List<Submission> getAllSubmission();

    //public Submission updateCalculationByUsername(String id, Submission cal) throws SubmissionNotFoundException;

    public Submission saveSubmission(Submission submission);


}
