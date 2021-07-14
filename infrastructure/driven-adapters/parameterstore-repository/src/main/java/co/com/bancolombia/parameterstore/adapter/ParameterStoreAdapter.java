package co.com.bancolombia.parameterstore.adapter;

import co.com.bancolombia.model.parametermodel.ParameterModel;
import co.com.bancolombia.model.parametermodel.gateways.ParameterModelRepository;
import co.com.bancolombia.parameterstore.operations.ParameterStoreOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ParameterStoreAdapter implements ParameterModelRepository {

    private final ParameterStoreOperations operations;

    @Override
    public Mono<ParameterModel> getParameter(String parameterName) {
        return operations.getParameter(parameterName, ParameterModel.class);
    }
}