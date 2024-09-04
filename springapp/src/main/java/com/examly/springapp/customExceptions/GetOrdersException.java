package com.examly.springapp.customExceptions;

public class GetOrdersException extends RuntimeException{

    public GetOrdersException(String message){

        super(message);

    }

}
