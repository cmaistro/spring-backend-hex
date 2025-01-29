package com.example.backend.infrastructure.database.repository;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.DescribeTableEnhancedResponse;

import java.util.Optional;
import java.util.UUID;

public abstract class DynamoDBRepository<T extends BaseEntity> {


    private final DynamoDbTable<T> table;

    public DynamoDBRepository(DynamoDbEnhancedClient enhancedClient) {
        this.table =  enhancedClient.table(getTableName(), getTableSchema());
    }

    public T save(T entity) {
        entity.setId(UUID.randomUUID().toString());
        table.putItem(entity);
        return entity;
    }

    public void update(T entity) {
        table.updateItem(entity);
    }

    public Optional<T> findById(String id){
        return Optional.ofNullable(table.getItem(r->r.key(k->k.partitionValue(id))));
    }

    public void deleteById(String id) {
        table.deleteItem(r->r.key(k->k.partitionValue(id)));
    }

    protected abstract TableSchema<T> getTableSchema();

    protected abstract String getTableName();

    public DescribeTableEnhancedResponse getTableDescription() {
        return table.describeTable();
    }

}
