package com.example.backend.domain.service;

import com.example.backend.domain.model.Customer;
import com.example.backend.domain.ports.output.CustomerPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerPort customerPort;

    public CustomerService(CustomerPort customerPort) {
        this.customerPort = customerPort;
    }

    public Customer createUser(Customer customer) {
        return customerPort.createUser(customer);
    }

    public Customer getUserById(UUID id) {
        return customerPort.getCustomerById(id);
    }

    public void updateUser(UUID id, Customer customer) {
        customerPort.updateUser(id, customer);
    }
}
