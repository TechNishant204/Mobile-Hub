package com.examly.springapp.customExceptions;

public class CartNotFoundException extends RuntimeException{

    public CartNotFoundException(String message){

        super(message);
        
    }

}
