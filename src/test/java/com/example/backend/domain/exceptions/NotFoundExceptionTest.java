package com.example.backend.domain.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotFoundExceptionTest {

    @Test
    void constructor_WithMessage() {
        String message = "Test error message";
        NotFoundException exception = new NotFoundException(message);

        assertEquals(message, exception.getMessage());
    }

    @Test
    void constructor_WithMessageAndCause() {
        String message = "Test error message";
        Throwable cause = new RuntimeException("Cause");
        NotFoundException exception = new NotFoundException(message, cause);

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void exceptionInheritance() {
        NotFoundException exception = new NotFoundException("Test");
        assertTrue(exception instanceof RuntimeException);
    }
}
