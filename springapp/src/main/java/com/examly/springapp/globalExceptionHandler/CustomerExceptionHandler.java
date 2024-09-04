package com.examly.springapp.globalExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.examly.springapp.customExceptions.CustomerNotFoundException;
import com.examly.springapp.customExceptions.RegisterCustomerException;

@RestControllerAdvice
public class CustomerExceptionHandler {


    @ExceptionHandler(RegisterCustomerException.class)
    public ResponseEntity<String>  customerRegistrationException(RegisterCustomerException e){


         return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> customerNotFound(CustomerNotFoundException e){

         return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

}
