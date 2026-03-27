package com.guilherme.todoapi.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//------------------------------------------------------------------------------------------------------------
@RestControllerAdvice
public class GlobalExceptionHandler extends Exception {

    // Trata erro 404 - Não encontrado
   @ExceptionHandler(ResourceNotFoundException.class)
   @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(ResourceNotFoundException ex){
       return ex.getMessage();
   }

}
