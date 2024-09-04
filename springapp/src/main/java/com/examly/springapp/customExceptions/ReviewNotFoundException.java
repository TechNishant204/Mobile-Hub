package com.examly.springapp.customExceptions;

public class ReviewNotFoundException extends RuntimeException{

    public ReviewNotFoundException(String message){

        super(message);
        
    }

}
