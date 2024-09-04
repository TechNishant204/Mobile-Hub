package com.examly.springapp.customExceptions;

public class DuplicateMobileNumberException extends RuntimeException  {


    public DuplicateMobileNumberException(String message){

          super(message);
    }

}
