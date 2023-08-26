package com.interbank.periferiait.springrestexchangerate.infraestructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessValidationException extends  RuntimeException{
    public BusinessValidationException(String message){
        super(message);
    }
}
