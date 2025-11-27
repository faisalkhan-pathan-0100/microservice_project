package com.faisal.user.service.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){
        super("Resource Not Found on Server");
    }

    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
