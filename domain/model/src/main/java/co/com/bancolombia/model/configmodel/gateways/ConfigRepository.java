package co.com.bancolombia.model.configmodel.gateways;

import co.com.bancolombia.model.configmodel.ConfigModel;
import reactor.core.publisher.Mono;

public interface ConfigRepository {
    Mono<ConfigModel> getModel(String id);
}
