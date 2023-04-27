package com.nbr.trp.submission.service;

import com.nbr.trp.submission.entity.Submission;
import com.nbr.trp.submission.exception.SubmissionNotFoundException;
import com.nbr.trp.submission.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionServiceImpl implements SubmissionService {
    @Autowired
    private SubmissionRepository submissionRepository;

    @Override
    public Submission getSubmissionByRuid(String id) {
        Submission submission = submissionRepository.findByRuid(id);
        return submission;
    }

    @Override
    public Submission getSubmissionByTin(String tin) {
        Submission submission = submissionRepository.findByRTin(tin);
        return submission;
    }

    @Override
    public List<Submission> getAllSubmission() {
        List<Submission> sb = submissionRepository.findAll();
        return sb;
    }

    @Override
    public Submission saveSubmission(Submission submission) {
        Submission sb = submissionRepository.save(submission);
        return sb;
    }
}
