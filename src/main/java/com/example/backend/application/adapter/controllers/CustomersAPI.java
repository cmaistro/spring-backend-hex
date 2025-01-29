package com.example.backend.application.adapter.controllers;


import com.example.backend.application.adapter.controllers.dto.CustomerDTO;
import com.example.backend.application.adapter.controllers.dto.CreateCustomer201Response;
import com.example.backend.application.adapter.controllers.mappers.CustomerMapper;
import com.example.backend.domain.model.Customer;
import com.example.backend.domain.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;


import java.util.UUID;

@Controller
public class CustomersAPI implements DefaultApi {

    private final CustomerService customerService;

    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;


    public CustomersAPI(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<CreateCustomer201Response> createCustomer(
            CustomerDTO customerDTO
    ) {

        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        Customer createdCustomer = customerService.createUser(customer);

        return new ResponseEntity<>(new CreateCustomer201Response().id(createdCustomer.getId()), HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<CustomerDTO> getCustomerById(UUID userId) {

        Customer customer = customerService.getUserById(userId);

        return new ResponseEntity<>(customerMapper.customerToCustomerDTO(customer), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Void> updateCustomer(
            UUID userId,
            CustomerDTO customerDTO
    ) {

        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customerService.updateUser(userId, customer);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
