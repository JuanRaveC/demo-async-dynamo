package co.com.bancolombia.usecase.config;

import co.com.bancolombia.model.configmodel.ConfigModel;
import co.com.bancolombia.model.configmodel.gateways.ConfigRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ConfigUseCase {

    private final ConfigRepository repository;

    public Mono<ConfigModel> getModel(String id){
        return repository.getModel(id);
    }
}
