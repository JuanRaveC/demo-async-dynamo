package co.com.bancolombia.config;

import co.com.bancolombia.model.configmodel.gateways.ConfigRepository;
import co.com.bancolombia.usecase.config.ConfigUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.regions.Region;

@Configuration
public class DynamoConfig {

    @Bean
    public DynamoDbAsyncClient dynamoDbAsyncClient() {
        return DynamoDbAsyncClient.builder()
                .region(Region.US_EAST_1)
                .build();
    }
}