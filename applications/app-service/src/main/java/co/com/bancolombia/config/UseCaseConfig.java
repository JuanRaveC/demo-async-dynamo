package co.com.bancolombia.config;

import co.com.bancolombia.model.configmodel.gateways.ConfigRepository;
import co.com.bancolombia.model.parametermodel.gateways.ParameterModelRepository;
import co.com.bancolombia.usecase.config.ConfigUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    @Bean
    public ConfigUseCase configUseCase(ConfigRepository repository,
                                       ParameterModelRepository parameter) {
        return new ConfigUseCase(repository, parameter);
    }
}
