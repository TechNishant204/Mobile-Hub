package com.examly.springapp.globalExceptionHandler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.examly.springapp.customExceptions.DuplicateEmailException;
import com.examly.springapp.customExceptions.DuplicateMobileNumberException;
import com.examly.springapp.customExceptions.DuplicateUserException;
import com.examly.springapp.model.ErrorDTO;

@RestControllerAdvice
public class DuplicateEntityExceptionCustom {


    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<ErrorDTO> duplicateUserException(DuplicateUserException e){

             ErrorDTO errorDTO=new ErrorDTO();

             errorDTO.setErrorMessage(e.getMessage());
             errorDTO.setCause("duplicate user id are not allowed");
             errorDTO.setException(e);
             errorDTO.setStatusCode(409);

        return new ResponseEntity<>(errorDTO,HttpStatus.CONFLICT);
    }
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorDTO> duplicateEmailException(DuplicateEmailException e){

             ErrorDTO errorDTO=new ErrorDTO();

             errorDTO.setErrorMessage(e.getMessage());
             errorDTO.setCause("duplicate email id are not allowed");
             errorDTO.setException(e);
             errorDTO.setStatusCode(409);

        return new ResponseEntity<>(errorDTO,HttpStatus.CONFLICT);
    }
 
    @ExceptionHandler(DuplicateMobileNumberException.class)
    public ResponseEntity<ErrorDTO> duplicateMobileNumberException(DuplicateMobileNumberException e){

                    ErrorDTO errorDTO=new ErrorDTO();

                    errorDTO.setErrorMessage(e.getMessage());
                    errorDTO.setCause("duplicate mobile number are not allowed");
                    errorDTO.setException(e);
                    errorDTO.setStatusCode(409);

        return new ResponseEntity<>(errorDTO,HttpStatus.CONFLICT);
    }
 
    
}
