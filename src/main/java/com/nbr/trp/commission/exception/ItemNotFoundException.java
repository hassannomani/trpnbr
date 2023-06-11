package com.nbr.trp.commission.exception;

public class ItemNotFoundException extends Exception{
    private String message;

    public ItemNotFoundException(String message){
        super(message);
        this.message=message;
    }

    public ItemNotFoundException() {}
}
