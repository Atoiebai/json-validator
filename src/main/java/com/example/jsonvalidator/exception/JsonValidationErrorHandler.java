package com.example.jsonvalidator.exception;


import com.example.jsonvalidator.exception.JsonSchemaLoadingFailedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class JsonValidationErrorHandler {

    @ExceptionHandler(JsonSchemaLoadingFailedException.class)
    public ResponseEntity<Map<String, Object>> onJsonValidationFailedException(JsonSchemaLoadingFailedException ex) {

        return ResponseEntity.badRequest().body(Map.of(
                "message", "Json validation failed",
                "details", ex.getMessage()
        ));
    }
}