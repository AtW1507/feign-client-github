package com.zadanie02.infrastructure.controller.error.handlers;

import com.zadanie02.infrastructure.controller.error.ErrorHeader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice()
public class HeaderHandler {

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ErrorHeader> handleException(){
        ErrorHeader error = new ErrorHeader(HttpStatus.NOT_ACCEPTABLE, "Accept header must be application/json");

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(error);
    }
}
