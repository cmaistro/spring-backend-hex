package com.example.backend.domain.ports.output;

import com.example.backend.domain.model.Customer;

import java.util.UUID;

public interface CustomerPort {

    Customer createUser(Customer customer);

    void updateUser(UUID id, Customer customer);

    void deleteCustomer(UUID id);

    Customer getCustomerById(UUID id);

}
