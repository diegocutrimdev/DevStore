package com.domain.devstore_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpServletRequest;
import com.domain.devstore_backend.dto.CustomError;
import com.domain.devstore_backend.dto.FieldMessage;
import com.domain.devstore_backend.dto.ValidationError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
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


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {

        List<FieldMessage> errors = ex.getBindingResult().getFieldErrors().stream().map(
                fe -> new FieldMessage(fe.getField(), fe.getDefaultMessage())).toList();

        var validationError = new ValidationError(Instant.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Invalid data", request.getRequestURI(), errors);

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(validationError);
    }
}
