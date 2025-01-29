package com.example.backend.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.example.backend.application.adapter.controllers.dto.CustomerDTO;
import com.example.backend.domain.model.Customer;

public class CustomerFactories {

    public static CustomerDTO getValidNewCustomerDTOFactory() {
        return new CustomerDTO.Builder()
                .name("João da Silva")
                .email("teste@teste.com")
                .birthDate(LocalDate.ofYearDay(1980, 150))
                .phone("11999999999")
                .role("USER")
                .build();
    }

    public static Customer getValidExistentUserFactory() {
        return Customer.builder()
                .id(UUID.randomUUID())
                .name("João da Silva")
                .email("teste@teste.com")
                .birthDate(LocalDate.ofYearDay(1980, 150))
                .phone("11999999999")
                .role("USER")
                .build();
    }

    public static Customer getValidCustomerFromCustomerDTOFactory(CustomerDTO customerDTO, UUID id) {
        return Customer.builder()
                .id(id)
                .name(customerDTO.getName())
                .phone(customerDTO.getPhone())
                .email(customerDTO.getEmail())
                .birthDate(customerDTO.getBirthDate())
                .role(customerDTO.getRole())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static CustomerDTO getValidCustomerDTOFromUserFactory(Customer customer) {
        return new CustomerDTO.Builder()
                .id(customer.getId())
                .name(customer.getName())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .birthDate(customer.getBirthDate())
                .role(customer.getRole())
                .build();
    }

}
