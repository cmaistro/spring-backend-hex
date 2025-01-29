package com.example.backend.application.adapter.controllers.handlers;

import com.example.backend.domain.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.example.backend.application.adapter.controllers.dto.ErrorDTO;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ErrorDTO> handleNotFoundException(
            NotFoundException ex, WebRequest request) {
        ErrorDTO error = new ErrorDTO();
        error.setCode("404");
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value
            = { RuntimeException.class, Exception.class })
    protected ResponseEntity<ErrorDTO> handleConflict(
            Exception ex, WebRequest request) {
        ErrorDTO error = new ErrorDTO();
        error.setCode("500");
        error.setMessage("Unexpected error occurred");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
