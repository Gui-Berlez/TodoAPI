package com.guilherme.todoapi.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

//------------------------------------------------------------------------------------------------------------
@RestControllerAdvice
public class GlobalExceptionHandler{

    // Trata erro 404 - Recurso não encontrado
   @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex){
       ErrorResponse error = new ErrorResponse(404, ex.getMessage());
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error); // status + corpo juntos
   }

    // Trata erro 400 — validação de campos
   @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex){
        Map<String, String> campos = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            campos.put(error.getField(), error.getDefaultMessage());
        });

        Map<String, Object> response = new HashMap<>();
        response.put("status", 400);
        response.put("mensagem", "Erro de validação");
        response.put("campos", campos);

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); // status + corpo juntos

    }

    // Trata erro 400 — login/senha inválidos
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex){
       ErrorResponse error = new ErrorResponse(400, ex.getMessage());
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error); // status + corpo juntos
    }
}
