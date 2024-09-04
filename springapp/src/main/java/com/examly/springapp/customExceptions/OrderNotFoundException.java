package com.examly.springapp.customExceptions;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String message){

        super(message);

    }

}
