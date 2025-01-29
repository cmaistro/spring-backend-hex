package com.example.backend.application.adapter.controllers.dto;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class CreateCustomer201ResponseTest {

    @Test
    void testGettersAndSetters() {
        UUID id = UUID.randomUUID();
        CreateCustomer201Response response = new CreateCustomer201Response();
        response.setId(id);

        assertEquals(id, response.getId());
    }

    @Test
    void testEqualsAndHashCode() {
        UUID id = UUID.randomUUID();
        
        CreateCustomer201Response response1 = new CreateCustomer201Response();
        response1.setId(id);

        CreateCustomer201Response response2 = new CreateCustomer201Response();
        response2.setId(id);

        CreateCustomer201Response response3 = new CreateCustomer201Response();
        response3.setId(UUID.randomUUID());

        assertEquals(response1, response2);
        assertEquals(response1.hashCode(), response2.hashCode());
        assertNotEquals(response1, response3);
        assertNotEquals(response1.hashCode(), response3.hashCode());
    }

    @Test
    void testToString() {
        UUID id = UUID.randomUUID();
        CreateCustomer201Response response = new CreateCustomer201Response();
        response.setId(id);

        String toString = response.toString();
        assertTrue(toString.contains(id.toString()));
    }

    @Test
    void testNullValues() {
        CreateCustomer201Response response = new CreateCustomer201Response();
        assertNull(response.getId());
    }

    @Test
    void testBuilder() {
        UUID id = UUID.randomUUID();
        CreateCustomer201Response response = new CreateCustomer201Response().id(id);
        
        assertEquals(id, response.getId());
    }
}
