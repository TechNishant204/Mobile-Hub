package com.examly.springapp.customExceptions;

public class DuplicateUserException extends RuntimeException {

    public DuplicateUserException(String message){

          super(message);
    }

}
