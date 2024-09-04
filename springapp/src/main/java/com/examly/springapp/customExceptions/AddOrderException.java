package com.examly.springapp.customExceptions;

public class AddOrderException extends RuntimeException {

    public AddOrderException(String message){

        super(message);
        
    }

}
