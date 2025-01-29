package com.example.backend.application.adapter.controllers.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDTOTest {

    @Test
    void testBuilder() {
        UUID id = UUID.randomUUID();
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        
        CustomerDTO dto = new CustomerDTO.Builder()
                .id(id)
                .name("John Doe")
                .email("john@example.com")
                .phone("1234567890")
                .birthDate(birthDate)
                .role("USER")
                .build();

        assertEquals(id, dto.getId());
        assertEquals("John Doe", dto.getName());
        assertEquals("john@example.com", dto.getEmail());
        assertEquals("1234567890", dto.getPhone());
        assertEquals(birthDate, dto.getBirthDate());
        assertEquals("USER", dto.getRole());
    }

    @Test
    void testEqualsAndHashCode() {
        UUID id = UUID.randomUUID();
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        
        CustomerDTO dto1 = new CustomerDTO.Builder()
                .id(id)
                .name("John Doe")
                .email("john@example.com")
                .build();

        CustomerDTO dto2 = new CustomerDTO.Builder()
                .id(id)
                .name("John Doe")
                .email("john@example.com")
                .build();

        CustomerDTO dto3 = new CustomerDTO.Builder()
                .id(UUID.randomUUID())
                .name("Jane Doe")
                .email("jane@example.com")
                .build();

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1, dto3);
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
    }

    @Test
    void testToString() {
        CustomerDTO dto = new CustomerDTO.Builder()
                .name("John Doe")
                .email("john@example.com")
                .build();

        String toString = dto.toString();
        assertTrue(toString.contains("John Doe"));
        assertTrue(toString.contains("john@example.com"));
    }

    @Test
    void testNullValues() {
        CustomerDTO dto = new CustomerDTO.Builder().build();
        
        assertNull(dto.getId());
        assertNull(dto.getName());
        assertNull(dto.getEmail());
        assertNull(dto.getPhone());
        assertNull(dto.getBirthDate());
        assertNull(dto.getRole());
    }
}
