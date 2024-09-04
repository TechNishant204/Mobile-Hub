package com.examly.springapp.customExceptions;

public class RegisterCustomerException extends RuntimeException {


    public RegisterCustomerException(String message){

         super(message);
    }

}
