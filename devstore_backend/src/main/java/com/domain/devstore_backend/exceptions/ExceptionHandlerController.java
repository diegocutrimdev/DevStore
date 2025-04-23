package com.domain.devstore_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpServletRequest;
import com.domain.devstore_backend.dto.CustomError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> notFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        var customError = new CustomError(Instant.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customError);
    }


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<CustomError> badRequestException(BadRequestException ex, HttpServletRequest request) {
        var customError = new CustomError(Instant.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customError);
    }
}
