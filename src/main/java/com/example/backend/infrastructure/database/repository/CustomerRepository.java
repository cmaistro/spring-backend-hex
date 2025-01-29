package com.example.backend.infrastructure.database.repository;

import com.example.backend.infrastructure.database.entity.CustomerEntity;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttributeTags;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class CustomerRepository extends DynamoDBRepository<CustomerEntity> {

    public CustomerRepository(DynamoDbEnhancedClient enhancedClient) {
        super(enhancedClient);
    }

    @Override
    protected TableSchema<CustomerEntity> getTableSchema() {
        return TableSchema.builder(CustomerEntity.class)
                .newItemSupplier(CustomerEntity::new)
                .addAttribute(String.class, a -> a.name("id")
                        .getter(CustomerEntity::getId)
                        .setter(CustomerEntity::setId)
                        .tags(StaticAttributeTags.primaryPartitionKey()))
                .addAttribute(String.class, a -> a.name("name")
                        .getter(CustomerEntity::getName)
                        .setter(CustomerEntity::setName))
                .addAttribute(String.class, a -> a.name("email")
                        .getter(CustomerEntity::getEmail)
                        .setter(CustomerEntity::setEmail)
                        .tags(StaticAttributeTags.secondarySortKey("email")))
                .addAttribute(String.class, a -> a.name("phone")
                        .getter(CustomerEntity::getPhone)
                        .setter(CustomerEntity::setPhone))
                .addAttribute(LocalDate.class, a -> a.name("birthdate")
                        .getter(CustomerEntity::getBirthDate)
                        .setter(CustomerEntity::setBirthDate))
                .addAttribute(String.class, a -> a.name("role")
                        .getter(CustomerEntity::getRole)
                        .setter(CustomerEntity::setRole))
                .addAttribute(LocalDateTime.class, a -> a.name("createdAt")
                        .getter(CustomerEntity::getCreatedAt)
                        .setter(CustomerEntity::setCreatedAt))
                .addAttribute(LocalDateTime.class, a -> a.name("updatedAt")
                        .getter(CustomerEntity::getUpdatedAt)
                        .setter(CustomerEntity::setUpdatedAt))
                .build();
    }

    @Override
    protected String getTableName() {
        return "Customer";
    }

}
