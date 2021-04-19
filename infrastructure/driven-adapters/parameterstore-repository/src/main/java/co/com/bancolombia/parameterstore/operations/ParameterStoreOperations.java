package co.com.bancolombia.parameterstore.operations;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.ssm.SsmAsyncClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;
import software.amazon.awssdk.services.ssm.model.SsmException;

import java.lang.reflect.Type;


@Component
@RequiredArgsConstructor
@Slf4j
public class ParameterStoreOperations {

    private final SsmAsyncClient client;

    public <T> Mono<T> getParameter(String parameterName, Type typeOfT) {
        if (parameterName == null) {
            return null;
        }
        Gson gson = new Gson();
        try {
            GetParameterRequest parameterRequest = GetParameterRequest.builder()
                    .name(parameterName)
                    .build();
            return Mono.fromFuture(client.getParameter(parameterRequest))
                    .map(response -> gson.fromJson(response.parameter().value(), typeOfT));
        } catch (SsmException e) {
            log.info("Error fetching parameter from ParameterStore Service" + e.getMessage());
            return null;
        } catch (JsonParseException e) {
            log.info("Error parsing response to class: " + typeOfT.getTypeName() + " provided.");
            return null;
        }
    }

}
