package com.examly.springapp.globalExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.examly.springapp.customExceptions.UserNotFoundWithNameException;

@RestControllerAdvice
public class LoginIssueExceptionHandling {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> LoginIssueException(UsernameNotFoundException e){

        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);

    }

}
