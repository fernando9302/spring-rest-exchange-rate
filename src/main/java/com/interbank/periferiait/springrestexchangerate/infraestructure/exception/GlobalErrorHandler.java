package com.interbank.periferiait.springrestexchangerate.infraestructure.exception;

import com.interbank.periferiait.springrestexchangerate.infraestructure.generics.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessValidationException.class)
    public ResponseEntity<GenericResponse> handleBusinessValidationException(BusinessValidationException ex, WebRequest req) {
        GenericResponse res = new GenericResponse();
        res.getErrors().add(ex.getMessage());
        res.setDate(LocalDateTime.now());
        res.setProcess(false);
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
}