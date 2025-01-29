package com.example.backend.infrastructure.database.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@Configuration
public class DynamoDBConfig {

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;

    @Value("${amazon.aws.region}")
    private String amazonAwsRegion;

    @Value("${amazon.aws.profile}")
    private String amazonAwsProfile;

    @Bean
    public DynamoDbEnhancedClient enhancedClient() {
        ProfileCredentialsProvider awsCredentials = ProfileCredentialsProvider.builder().build();

        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(DynamoDbClient.builder()
                        .credentialsProvider(awsCredentials)
                        .region(Region.of(amazonAwsRegion))
                        .endpointOverride(URI.create(amazonDynamoDBEndpoint))
                        .build())
                .build();

    }

    @Bean
    public ProfileCredentialsProvider amazonAWSCredentials() {

        return ProfileCredentialsProvider.builder()
            .profileName(amazonAwsProfile)
            .build();

    }
}