package com.nbr.trp.submission.exception;

public class SubmissionNotFoundException extends Exception{
    private String message;

    public SubmissionNotFoundException(String message){
        super(message);
        this.message=message;
    }

    public SubmissionNotFoundException() {}
}
