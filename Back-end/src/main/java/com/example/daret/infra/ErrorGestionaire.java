package com.example.daret.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ErrorGestionaire {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> error404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> error400(MethodArgumentNotValidException ex){
        var error = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(error.stream().map(dataerror::new).toList());
    }

    public record  dataerror(String message, String field) {
        public dataerror(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}