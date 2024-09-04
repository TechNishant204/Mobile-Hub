package com.examly.springapp.customExceptions;


public class NoCustomersFound extends RuntimeException {

    public NoCustomersFound(String message){

         super(message);
    }

}
