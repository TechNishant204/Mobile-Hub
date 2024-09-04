package com.examly.springapp.customExceptions;

public class MobileNotFoundException extends RuntimeException {


        public MobileNotFoundException(String message){
    
            super(message);
            
        }

        public MobileNotFoundException(String message,Throwable error){
    
            super(message,error);
            
        }

}
