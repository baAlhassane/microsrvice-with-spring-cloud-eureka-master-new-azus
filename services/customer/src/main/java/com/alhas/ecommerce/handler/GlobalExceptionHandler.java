package com.alhas.ecommerce.handler;


import com.alhas.ecommerce.exception.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handle(CustomerNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> handle(MethodArgumentNotValidException e) {

        var errors= new HashMap<String,String>();
        e.getBindingResult()
                .getAllErrors().
                forEach((error) -> {
                    var fieldName = ((FieldError) error).getField();
                    var errorDefaultMessage= error.getDefaultMessage();
                    errors.put(fieldName, errorDefaultMessage);

                });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErroResponse(errors));
    }
}
