package com.examly.springapp.customExceptions;

public class CartHasNoMobiles extends RuntimeException {


    public CartHasNoMobiles(String message){

        super(message);
    }

    public CartHasNoMobiles(String message,Throwable error){

        super(message,error);
    }

    
}
