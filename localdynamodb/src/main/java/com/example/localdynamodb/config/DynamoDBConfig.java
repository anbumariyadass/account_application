package com.example.localdynamodb.config;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDBConfig {
	
    @Bean
    DynamoDbClient dynamoDbClient() {
        return DynamoDbClient.builder()
                .endpointOverride(URI.create("http://localhost:8000")) // Use local DynamoDB
                .region(Region.US_EAST_1) // Dummy region, required
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(
                        "fakeMyKeyId", "fakeSecretAccessKey"))) // Dummy credentials for local testing
                .overrideConfiguration(ClientOverrideConfiguration.builder().build())
                .build();
    }
    
    @Bean
    DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient) //Use the configured DynamoDbClient
                .build();
    }
}

