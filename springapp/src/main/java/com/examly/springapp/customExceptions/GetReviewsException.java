package com.examly.springapp.customExceptions;

public class GetReviewsException extends RuntimeException {

    public GetReviewsException(String message){

        super(message);
        
    }

}
