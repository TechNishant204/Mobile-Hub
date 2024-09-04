package com.examly.springapp.customExceptions;


public class DuplicateEmailException extends RuntimeException {

       public DuplicateEmailException(String message,Throwable error ){

            super(message,error);
       }

       public DuplicateEmailException(String message){

           super(message);
       }
}
