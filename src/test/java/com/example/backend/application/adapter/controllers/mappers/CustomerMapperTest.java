package com.example.backend.application.adapter.controllers.mappers;

import com.example.backend.application.adapter.controllers.dto.CustomerDTO;
import com.example.backend.domain.model.Customer;
import com.example.backend.utils.CustomerFactories;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    private final CustomerMapper mapper = CustomerMapper.INSTANCE;

    @Test
    void customerToCustomerDTO_Success() {
        Customer customer = CustomerFactories.getValidExistentUserFactory();

        CustomerDTO dto = mapper.customerToCustomerDTO(customer);

        assertNotNull(dto);
        assertEquals(customer.getId(), dto.getId());
        assertEquals(customer.getName(), dto.getName());
        assertEquals(customer.getEmail(), dto.getEmail());
        assertEquals(customer.getPhone(), dto.getPhone());
        assertEquals(customer.getBirthDate(), dto.getBirthDate());
        assertEquals(customer.getRole(), dto.getRole());
    }

    @Test
    void customerDTOToCustomer_Success() {
        CustomerDTO dto = CustomerFactories.getValidNewCustomerDTOFactory();

        Customer customer = mapper.customerDTOToCustomer(dto);

        assertNotNull(customer);
        assertEquals(dto.getName(), customer.getName());
        assertEquals(dto.getEmail(), customer.getEmail());
        assertEquals(dto.getPhone(), customer.getPhone());
        assertEquals(dto.getBirthDate(), customer.getBirthDate());
        assertEquals(dto.getRole(), customer.getRole());
    }

    @Test
    void customerToCustomerDTO_NullInput() {
        CustomerDTO dto = mapper.customerToCustomerDTO(null);
        assertNull(dto);
    }

    @Test
    void customerDTOToCustomer_NullInput() {
        Customer customer = mapper.customerDTOToCustomer(null);
        assertNull(customer);
    }
}
