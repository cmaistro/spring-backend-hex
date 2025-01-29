package com.example.backend.application.adapter.controllers;

import com.example.backend.application.adapter.controllers.dto.CreateCustomer201Response;
import com.example.backend.application.adapter.controllers.dto.CustomerDTO;
import com.example.backend.application.adapter.controllers.mappers.CustomerMapper;
import com.example.backend.domain.model.Customer;
import com.example.backend.domain.service.CustomerService;
import com.example.backend.utils.CustomerFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CustomersAPITest {

    @Mock
    private CustomerService customerService;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomersAPI customersApi;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customersApi = new CustomersAPI(customerService);
    }

    @Test
    void createUser_createsUserSuccessfully() {

        CustomerDTO customerDTO = CustomerFactories.getValidNewCustomerDTOFactory();
        Customer customer = CustomerFactories.getValidCustomerFromCustomerDTOFactory(customerDTO, null);
        Customer createdCustomer = CustomerFactories.getValidCustomerFromCustomerDTOFactory(customerDTO, UUID.randomUUID());

        when(customerMapper.customerDTOToCustomer(customerDTO)).thenReturn(customer);
        when(customerService.createUser(any(Customer.class))).thenReturn(createdCustomer);

        ResponseEntity<CreateCustomer201Response> response = customersApi.createCustomer(customerDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdCustomer.getId(), response.getBody().getId());
    }

    @Test
    void getUserById_returnsUserSuccessfully() {

        Customer customer = CustomerFactories.getValidExistentUserFactory();
        UUID customerId = customer.getId();
        CustomerDTO customerDTO = CustomerFactories.getValidCustomerDTOFromUserFactory(customer);

        when(customerService.getUserById(customerId)).thenReturn(customer);
        when(customerMapper.customerToCustomerDTO(customer)).thenReturn(customerDTO);

        ResponseEntity<CustomerDTO> response = customersApi.getCustomerById(customerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customerDTO, response.getBody());
    }

    @Test
    void updateUser_updatesUserSuccessfully() {

        Customer customer = CustomerFactories.getValidExistentUserFactory();
        CustomerDTO customerDTO = CustomerFactories.getValidCustomerDTOFromUserFactory(customer);
        UUID customerId = customer.getId();

        when(customerMapper.customerDTOToCustomer(customerDTO)).thenReturn(customer);

        ResponseEntity<Void> response = customersApi.updateCustomer(customerId, customerDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}