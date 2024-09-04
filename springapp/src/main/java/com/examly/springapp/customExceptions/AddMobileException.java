package com.examly.springapp.customExceptions;

public class AddMobileException extends RuntimeException{

    public AddMobileException(String message){

        super(message);
    

    } 
    public AddMobileException(String message,Throwable error){

        super(message,error);
    
    } 
}



