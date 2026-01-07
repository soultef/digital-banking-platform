package com.bank.auth_service.exception.handler;

import com.bank.auth_service.exception.UserExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<String> handleDuplicateEmail(UserExistException ex) {
        return ResponseEntity
                .status(409)
                .body(ex.getMessage());
    }

    // Optional: generic fallback
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAll(Exception ex) {
        return ResponseEntity
                .status(500)
                .body("Internal server error");
    }
}
