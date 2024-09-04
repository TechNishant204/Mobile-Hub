package com.examly.springapp.customExceptions;

public class AddUserException extends RuntimeException {

    public AddUserException(String message,Throwable error){

        super(message,error);
        
    }
    public AddUserException(String message){

         super(message);
    }
}
