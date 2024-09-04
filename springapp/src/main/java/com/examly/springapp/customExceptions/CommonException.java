package com.examly.springapp.customExceptions;



public class CommonException extends RuntimeException {


     public CommonException(String message){

         super(message);
     }
     public CommonException(String message,Throwable error){

         super(message,error);
     }
}
