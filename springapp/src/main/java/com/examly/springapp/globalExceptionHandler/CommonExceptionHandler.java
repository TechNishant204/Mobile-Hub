package com.examly.springapp.globalExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.examly.springapp.customExceptions.CommonException;
import com.examly.springapp.model.ErrorDTO;

@RestControllerAdvice
public class CommonExceptionHandler {


          @ExceptionHandler(CommonException.class)
          public ResponseEntity<ErrorDTO> commonError(CommonException e){

               ErrorDTO errorDTO=new ErrorDTO();

               errorDTO.setErrorMessage("somthing error occured");
               errorDTO.setException(e.getCause());
               errorDTO.setStatusCode(500);

                return new ResponseEntity<>(errorDTO,HttpStatus.INTERNAL_SERVER_ERROR);
          }
}
