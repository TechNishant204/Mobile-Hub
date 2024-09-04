package com.examly.springapp.customExceptions;

public class ReviewNotDeletedException extends RuntimeException{

    public ReviewNotDeletedException(String message){

        super(message);
        
    }

}
