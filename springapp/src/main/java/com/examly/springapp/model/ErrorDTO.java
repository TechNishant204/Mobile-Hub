package com.examly.springapp.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Component
public class ErrorDTO {

    private String errorMessage;
    private String cause;
    private int statusCode;
    private Throwable exception;

}
