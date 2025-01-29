package com.example.backend.integration;

import com.example.backend.application.adapter.controllers.dto.CreateCustomer201Response;
import com.example.backend.application.adapter.controllers.dto.CustomerDTO;
import com.example.backend.utils.CustomerFactories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class CustomerAPIIntegrationTest extends IntegrationTestBase {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldCreateAndRetrieveCustomer() {
        // Create customer
        CustomerDTO newCustomer = CustomerFactories.getValidNewCustomerDTOFactory();
        ResponseEntity<CreateCustomer201Response> createResponse = restTemplate.postForEntity(
                "/v1/customers",
                newCustomer,
                CreateCustomer201Response.class
        );

        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
        assertNotNull(createResponse.getBody());
        assertNotNull(createResponse.getBody().getId());

        // Retrieve customer
        ResponseEntity<CustomerDTO> getResponse = restTemplate.getForEntity(
                "/v1/customers/" + createResponse.getBody().getId(),
                CustomerDTO.class
        );

        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertNotNull(getResponse.getBody());
        assertEquals(newCustomer.getName(), getResponse.getBody().getName());
    }

    @Test
    void shouldUpdateCustomer() {
        // Create customer first
        CustomerDTO newCustomer = CustomerFactories.getValidNewCustomerDTOFactory();
        ResponseEntity<CreateCustomer201Response> createResponse = restTemplate.postForEntity(
                "/v1/customers",
                newCustomer,
                CreateCustomer201Response.class
        );

        // Update customer
        newCustomer.setName("Updated Name");
        ResponseEntity<Void> updateResponse = restTemplate.exchange(
                "/v1/customers/" + createResponse.getBody().getId(),
                HttpMethod.PUT,
                new HttpEntity<>(newCustomer),
                Void.class
        );

        assertEquals(HttpStatus.OK, updateResponse.getStatusCode());

        // Verify update
        ResponseEntity<CustomerDTO> getResponse = restTemplate.getForEntity(
                "/v1/customers/" + createResponse.getBody().getId(),
                CustomerDTO.class
        );

        assertEquals("Updated Name", getResponse.getBody().getName());
    }
}
