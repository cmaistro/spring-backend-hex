package com.example.backend.infrastructure.database;

import com.example.backend.domain.model.Customer;
import com.example.backend.infrastructure.database.entity.CustomerEntity;
import com.example.backend.infrastructure.database.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cache.CacheManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class CustomerRepositoryAdapterTest {

    @Mock
    private CustomerRepository repository;

    @Mock
    private CacheManager cacheManager;


    @InjectMocks
    private CustomerRepositoryAdapter adapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        adapter = new CustomerRepositoryAdapter(repository, cacheManager);
    }

    @Test
    public void testCreateUser() {

        Customer customer = new Customer(null, "John Doe", "john@example.com", "1234567890", LocalDate.now(), "USER", LocalDateTime.now(), LocalDateTime.now());
        CustomerEntity customerEntity = new CustomerEntity(UUID.randomUUID().toString(), "John Doe", "john@example.com", "1234567890", LocalDate.now(), "USER", LocalDateTime.now(), LocalDateTime.now());
        when(repository.save(any(CustomerEntity.class))).thenReturn(customerEntity);

        Customer createdCustomer = adapter.createUser(customer);

        assertNotNull(createdCustomer);

        verify(repository).save(any(CustomerEntity.class));
    }

    @Test
    public void testUpdateUser() {
        UUID id = UUID.randomUUID();
        Customer customer = new Customer(id, "John Doe", "john@example.com", "1234567890", LocalDate.now(), "USER", LocalDateTime.now(), LocalDateTime.now());
        CustomerEntity existingCustomerEntity = new CustomerEntity(id.toString(), "John Doe", "john@example.com", "1234567890", LocalDate.now(), "USER", LocalDateTime.now(), LocalDateTime.now());
        when(repository.findById(any(String.class))).thenReturn(Optional.of(existingCustomerEntity));

        adapter.updateUser(id, customer);

        verify(repository).findById(id.toString());
        verify(repository).update(existingCustomerEntity);
    }

    @Test
    public void testGetCustomerById() {
        UUID id = UUID.randomUUID();
        CustomerEntity customerEntity = new CustomerEntity(id.toString(), "John Doe", "john@example.com", "1234567890", LocalDate.now(), "USER", LocalDateTime.now(), LocalDateTime.now());
        when(repository.findById(id.toString())).thenReturn(Optional.of(customerEntity));

        Customer customer = adapter.getCustomerById(id);

        assertNotNull(customer);
        assertEquals(customerEntity.getId(), customer.getId().toString());
        verify(repository).findById(id.toString());
        //verify(mapper).userEntityToUser(userEntity);
    }

    @Test
    public void testDeleteCustomer() {
        UUID id = UUID.randomUUID();
        CustomerEntity customerEntity = new CustomerEntity(id.toString(), "John Doe", "john@example.com", "1234567890", LocalDate.now(), "USER", LocalDateTime.now(), LocalDateTime.now());
        when(repository.findById(id.toString())).thenReturn(Optional.of(customerEntity));

        adapter.deleteCustomer(id);

        verify(repository).deleteById(id.toString());
    }

}
