package com.example.backend.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration
public class TestConfig {

    @Bean
    public GenericContainer<?> dynamoDBContainer() {
        GenericContainer<?> dynamoDb = new GenericContainer<>(DockerImageName.parse("amazon/dynamodb-local:latest"))
                .withExposedPorts(8000);
        dynamoDb.start();
        System.setProperty("amazon.dynamodb.endpoint", "http://localhost:" + dynamoDb.getMappedPort(8000));
        return dynamoDb;
    }
}
