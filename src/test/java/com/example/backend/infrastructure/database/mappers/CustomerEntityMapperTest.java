package com.example.backend.infrastructure.database.mappers;

import com.example.backend.domain.model.Customer;
import com.example.backend.infrastructure.database.entity.CustomerEntity;
import com.example.backend.utils.CustomerFactories;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerEntityMapperTest {

    private final CustomerEntityMapper mapper = CustomerEntityMapper.INSTANCE;

    @Test
    void customerToCustomerEntity_Success() {
        Customer customer = CustomerFactories.getValidExistentUserFactory();

        CustomerEntity entity = mapper.customerToCustomerEntity(customer);

        assertNotNull(entity);
        assertEquals(customer.getId().toString(), entity.getId());
        assertEquals(customer.getName(), entity.getName());
        assertEquals(customer.getEmail(), entity.getEmail());
        assertEquals(customer.getPhone(), entity.getPhone());
        assertEquals(customer.getBirthDate(), entity.getBirthDate());
        assertEquals(customer.getRole(), entity.getRole());
    }

    @Test
    void customerEntityToCustomer_Success() {
        Customer originalCustomer = CustomerFactories.getValidExistentUserFactory();
        CustomerEntity entity = mapper.customerToCustomerEntity(originalCustomer);

        Customer customer = mapper.customerEntityToCustomer(entity);

        assertNotNull(customer);
        assertEquals(UUID.fromString(entity.getId()), customer.getId());
        assertEquals(entity.getName(), customer.getName());
        assertEquals(entity.getEmail(), customer.getEmail());
        assertEquals(entity.getPhone(), customer.getPhone());
        assertEquals(entity.getBirthDate(), customer.getBirthDate());
        assertEquals(entity.getRole(), customer.getRole());
    }

    @Test
    void customerToCustomerEntity_NullInput() {
        CustomerEntity entity = mapper.customerToCustomerEntity(null);
        assertNull(entity);
    }

    @Test
    void customerEntityToCustomer_NullInput() {
        Customer customer = mapper.customerEntityToCustomer(null);
        assertNull(customer);
    }
}
