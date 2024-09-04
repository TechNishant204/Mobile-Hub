package com.examly.springapp.globalExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.examly.springapp.customExceptions.AddMobileException;
import com.examly.springapp.customExceptions.GetMobilesException;
import com.examly.springapp.customExceptions.IfMobileNotDeletedException;
import com.examly.springapp.customExceptions.MobileNotFoundException;
import com.examly.springapp.model.ErrorDTO;

@RestControllerAdvice
public class MobileExceptionHandler {

    @ExceptionHandler(AddMobileException.class)
    public ResponseEntity<ErrorDTO>   mobileCannotBeAdded(AddMobileException e){

                ErrorDTO errorDTO=new ErrorDTO();
                errorDTO.setErrorMessage(e.getMessage());
                errorDTO.setCause(e.getLocalizedMessage());
                errorDTO.setException(e.getCause());
                errorDTO.setStatusCode(204);
        return new  ResponseEntity<>(errorDTO,HttpStatus.CONFLICT);    
    }

    @ExceptionHandler(GetMobilesException.class)
    public ResponseEntity<ErrorDTO>   getMobilesException(GetMobilesException e){

            ErrorDTO errorDTO=new ErrorDTO();
            errorDTO.setStatusCode(204);
            errorDTO.setCause(e.getLocalizedMessage());
            errorDTO.setErrorMessage(e.getMessage());
            errorDTO.setException(e.getCause());
        return new ResponseEntity<>(errorDTO,HttpStatus.NO_CONTENT);
    }
    
    @ExceptionHandler(MobileNotFoundException.class)
    public ResponseEntity<ErrorDTO>   getMobilesException(MobileNotFoundException e){

            ErrorDTO errorDTO=new ErrorDTO();
            errorDTO.setStatusCode(404);
            errorDTO.setCause(e.getLocalizedMessage());
            errorDTO.setErrorMessage(e.getMessage());
            errorDTO.setException(e.getCause());
        return new ResponseEntity<>(errorDTO,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IfMobileNotDeletedException.class)
    public ResponseEntity<ErrorDTO>   getMobilesException(IfMobileNotDeletedException e){

            ErrorDTO errorDTO=new ErrorDTO();
            errorDTO.setStatusCode(204);
            errorDTO.setCause(e.getLocalizedMessage());
            errorDTO.setErrorMessage(e.getMessage());
            errorDTO.setException(e.getCause());
        return new ResponseEntity<>(errorDTO,HttpStatus.CONFLICT);
    }


}
