package co.com.bancolombia.usecase.config;

import co.com.bancolombia.model.configmodel.ConfigModel;
import co.com.bancolombia.model.configmodel.gateways.ConfigRepository;
import co.com.bancolombia.model.parametermodel.ParameterModel;
import co.com.bancolombia.model.parametermodel.gateways.ParameterModelRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ConfigUseCase {

    private final ConfigRepository repository;
    private final ParameterModelRepository parameterModelRepository;


    public Mono<ConfigModel> getModel(String id) {
        return repository.getModel(id);
    }

    public Mono<ParameterModel> getParameter(String parameterName) {
        return parameterModelRepository.getParameter(parameterName);
    }

}
