package com.examly.springapp.customExceptions;

public class CartNotSavedException extends RuntimeException {


      public CartNotSavedException(String message){

           super(message);
      }

      public CartNotSavedException(String message,Throwable error){

         super(message,error);
      }
}
