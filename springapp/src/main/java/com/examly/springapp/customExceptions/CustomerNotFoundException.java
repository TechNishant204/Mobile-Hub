package com.examly.springapp.customExceptions;

public class CustomerNotFoundException extends RuntimeException {


    public CustomerNotFoundException(String message){

         super(message);
    }


}
