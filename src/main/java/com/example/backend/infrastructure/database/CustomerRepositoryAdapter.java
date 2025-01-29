package com.example.backend.infrastructure.database;

import com.example.backend.domain.exceptions.NotFoundException;
import com.example.backend.domain.model.Customer;
import com.example.backend.domain.ports.output.CustomerPort;
import com.example.backend.infrastructure.database.entity.CustomerEntity;
import com.example.backend.infrastructure.database.mappers.CustomerEntityMapper;
import com.example.backend.infrastructure.database.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class CustomerRepositoryAdapter implements CustomerPort {

    private final CustomerRepository repository;

    private final CustomerEntityMapper mapper = CustomerEntityMapper.INSTANCE;

    private final CacheManager cacheManager;

    @Autowired
    public CustomerRepositoryAdapter(CustomerRepository customerRepository, CacheManager cacheManager) {
        this.cacheManager = cacheManager;
        this.repository = customerRepository;
    }

    @Override
    public Customer createUser(Customer customer) {

        CustomerEntity customerEntity = mapper.customerToCustomerEntity(customer);
        customerEntity.setCreatedAt(LocalDateTime.now());
        customerEntity.setUpdatedAt(LocalDateTime.now());
        CustomerEntity savedCustomer = repository.save(customerEntity);
        return mapper.customerEntityToCustomer(savedCustomer);

    }

    @Override
    public void updateUser(UUID id, Customer customer) {

        CustomerEntity existingCustomer = repository.findById(id.toString()).orElseThrow(() -> new NotFoundException("Customer not found"));
        existingCustomer.setBirthDate(customer.getBirthDate());
        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPhone(customer.getPhone());
        existingCustomer.setRole(customer.getRole());
        existingCustomer.setUpdatedAt(LocalDateTime.now());
        repository.update(existingCustomer);

        try {
            // evict the cache
            cacheManager.getCache("customers").evictIfPresent(id);
        } catch (Exception e) {
            log.warn("Error evicting cache: {}", e.getMessage());
        }

    }

    @Override
    @Cacheable(value = "customers", key = "#id")
    public Customer getCustomerById(UUID id) {
        CustomerEntity customerEntity = repository.findById(id.toString()).orElseThrow(() -> new NotFoundException("Customer not found"));
        return mapper.customerEntityToCustomer(customerEntity);
    }

    @Override
    public void deleteCustomer(UUID id) {
        repository.deleteById(id.toString());
    }


}
