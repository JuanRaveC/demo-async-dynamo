package co.com.bancolombia.model.parametermodel.gateways;

import co.com.bancolombia.model.parametermodel.ParameterModel;
import reactor.core.publisher.Mono;

public interface ParameterModelRepository {
    Mono<ParameterModel> getParameter(String parameterName);
}
