package com.example.backend.domain.service;

import com.example.backend.domain.exceptions.NotFoundException;
import com.example.backend.domain.model.Customer;
import com.example.backend.domain.ports.output.CustomerPort;
import com.example.backend.utils.CustomerFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    private CustomerPort customerPort;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser_Success() {
        Customer customer = CustomerFactories.getValidExistentUserFactory();
        when(customerPort.createUser(any(Customer.class))).thenReturn(customer);

        Customer result = customerService.createUser(customer);

        assertNotNull(result);
        assertEquals(customer.getId(), result.getId());
        verify(customerPort).createUser(customer);
    }

    @Test
    void getUserById_Success() {
        Customer customer = CustomerFactories.getValidExistentUserFactory();
        when(customerPort.getCustomerById(any(UUID.class))).thenReturn(customer);

        Customer result = customerService.getUserById(customer.getId());

        assertNotNull(result);
        assertEquals(customer.getId(), result.getId());
        verify(customerPort).getCustomerById(customer.getId());
    }

    @Test
    void getUserById_NotFound() {
        UUID id = UUID.randomUUID();
        when(customerPort.getCustomerById(id)).thenThrow(new NotFoundException("Customer not found"));

        assertThrows(NotFoundException.class, () -> customerService.getUserById(id));
        verify(customerPort).getCustomerById(id);
    }

    @Test
    void updateUser_Success() {
        UUID id = UUID.randomUUID();
        Customer customer = CustomerFactories.getValidExistentUserFactory();
        doNothing().when(customerPort).updateUser(id, customer);

        assertDoesNotThrow(() -> customerService.updateUser(id, customer));
        verify(customerPort).updateUser(id, customer);
    }

    @Test
    void updateUser_NotFound() {
        UUID id = UUID.randomUUID();
        Customer customer = CustomerFactories.getValidExistentUserFactory();
        doThrow(new NotFoundException("Customer not found")).when(customerPort).updateUser(id, customer);

        assertThrows(NotFoundException.class, () -> customerService.updateUser(id, customer));
        verify(customerPort).updateUser(id, customer);
    }
}
