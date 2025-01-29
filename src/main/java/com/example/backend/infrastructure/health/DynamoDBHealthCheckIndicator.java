package com.example.backend.infrastructure.health;

import com.example.backend.infrastructure.database.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.model.DescribeTableEnhancedResponse;

@Component
public class DynamoDBHealthCheckIndicator implements HealthIndicator {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CustomerRepository customerRepository;

    @Autowired
    public DynamoDBHealthCheckIndicator(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Health health() {
        int errorCode = check();
        if (errorCode != 0) {
            return Health.down().withDetail("Error Code", errorCode).build();
        }
        return Health.up().build();
    }

    private int check() {

        try {
            DescribeTableEnhancedResponse tableDescription = customerRepository.getTableDescription();
        } catch (Exception e) {
            logger.error("Error while checking DynamoDB health: {}", e.getMessage());
            return 1;
        }
        return 0;
    }

}
