package co.com.bancolombia.api;

import co.com.bancolombia.model.configmodel.ConfigModel;
import co.com.bancolombia.model.parametermodel.ParameterModel;
import co.com.bancolombia.usecase.config.ConfigUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
    private final ConfigUseCase useCase;

    @GetMapping(path = "/path/{id}")
    public Mono<ConfigModel> commandName(@PathVariable String id) {
        return useCase.getModel(id);
    }

    @GetMapping(path = "/parameter/{id}")
    public Mono<ParameterModel> getParameter(@PathVariable String id) {
        return useCase.getParameter(id);
    }
}
