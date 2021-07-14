package co.com.bancolombia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmAsyncClient;

@Configuration
public class ParameterStoreConfig {

    //@Bean
    public SsmAsyncClient ssmAsyncClient() {
        return SsmAsyncClient.builder()
                .region(Region.US_EAST_1)
                .build();
    }

}
