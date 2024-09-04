package com.examly.springapp.customExceptions;

public class UserNotFoundWithNameException extends RuntimeException{

    public UserNotFoundWithNameException(String message,Throwable error){

        super(message,error);
        
    }

    public UserNotFoundWithNameException(String message){

         super(message);
    }

}
