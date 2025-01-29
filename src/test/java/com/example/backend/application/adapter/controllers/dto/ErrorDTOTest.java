package com.example.backend.application.adapter.controllers.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ErrorDTOTest {

    @Test
    void testGettersAndSetters() {
        ErrorDTO error = new ErrorDTO();
        error.setCode("404");
        error.setMessage("Not Found");

        assertEquals("404", error.getCode());
        assertEquals("Not Found", error.getMessage());
    }

    @Test
    void testEqualsAndHashCode() {
        ErrorDTO error1 = new ErrorDTO();
        error1.setCode("404");
        error1.setMessage("Not Found");

        ErrorDTO error2 = new ErrorDTO();
        error2.setCode("404");
        error2.setMessage("Not Found");

        ErrorDTO error3 = new ErrorDTO();
        error3.setCode("500");
        error3.setMessage("Server Error");

        assertEquals(error1, error2);
        assertEquals(error1.hashCode(), error2.hashCode());
        assertNotEquals(error1, error3);
        assertNotEquals(error1.hashCode(), error3.hashCode());
    }

    @Test
    void testToString() {
        ErrorDTO error = new ErrorDTO();
        error.setCode("404");
        error.setMessage("Not Found");

        String toString = error.toString();
        assertTrue(toString.contains("404"));
        assertTrue(toString.contains("Not Found"));
    }

    @Test
    void testNullValues() {
        ErrorDTO error = new ErrorDTO();
        
        assertNull(error.getCode());
        assertNull(error.getMessage());
    }
}
