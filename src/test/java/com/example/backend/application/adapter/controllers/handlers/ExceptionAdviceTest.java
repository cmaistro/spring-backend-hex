package com.example.backend.application.adapter.controllers.handlers;

import com.example.backend.application.adapter.controllers.dto.ErrorDTO;
import com.example.backend.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ExceptionAdviceTest {

    private ExceptionAdvice exceptionAdvice;
    private WebRequest webRequest;

    @BeforeEach
    void setUp() {
        exceptionAdvice = new ExceptionAdvice();
        webRequest = mock(WebRequest.class);
    }

    @Test
    void handleNotFoundException() {
        NotFoundException exception = new NotFoundException("Resource not found");
        
        ResponseEntity<ErrorDTO> response = exceptionAdvice.handleNotFoundException(exception, webRequest);
        
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("404", response.getBody().getCode());
        assertEquals("Resource not found", response.getBody().getMessage());
    }

    @Test
    void handleRuntimeException() {
        RuntimeException exception = new RuntimeException("Unexpected error");
        
        ResponseEntity<ErrorDTO> response = exceptionAdvice.handleConflict(exception, webRequest);
        
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("500", response.getBody().getCode());
        assertEquals("Unexpected error occurred", response.getBody().getMessage());
    }

    @Test
    void handleGenericException() {
        Exception exception = new Exception("Generic error");
        
        ResponseEntity<ErrorDTO> response = exceptionAdvice.handleConflict(exception, webRequest);
        
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("500", response.getBody().getCode());
        assertEquals("Unexpected error occurred", response.getBody().getMessage());
    }
}
