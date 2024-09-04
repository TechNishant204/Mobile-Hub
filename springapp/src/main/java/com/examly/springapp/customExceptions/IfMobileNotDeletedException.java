package com.examly.springapp.customExceptions;

public class IfMobileNotDeletedException extends RuntimeException {

    public IfMobileNotDeletedException(String message){

        super(message);
        
    }
}
