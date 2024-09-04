package com.examly.springapp.customExceptions;

public class AddCartException extends RuntimeException{

    public AddCartException(String message){

        super(message);
        
    }

}
