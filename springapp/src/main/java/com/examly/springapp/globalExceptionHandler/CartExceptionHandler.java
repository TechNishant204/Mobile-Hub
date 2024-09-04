package com.examly.springapp.globalExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.examly.springapp.customExceptions.CartHasNoMobiles;
import com.examly.springapp.model.ErrorDTO;

@RestControllerAdvice
public class CartExceptionHandler {


    @ExceptionHandler(CartHasNoMobiles.class)
    public ResponseEntity<ErrorDTO>  cartHasNoMobiles(CartHasNoMobiles e){

                 ErrorDTO errorDTO=new ErrorDTO();

                 errorDTO.setStatusCode(204);
                 errorDTO.setErrorMessage(e.getMessage());
                 errorDTO.setCause(e.getLocalizedMessage());
                 errorDTO.setException(e.getCause());
        return  new ResponseEntity<>(errorDTO,HttpStatus.NO_CONTENT);
    }

}
