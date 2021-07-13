package co.com.bancolombia.parameterstore.operations;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.services.ssm.SsmAsyncClient;
import software.amazon.awssdk.services.ssm.model.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Component
@RequiredArgsConstructor
@Slf4j
public class ParameterStoreOperations {

    private final SsmAsyncClient client;

    public <T> Mono<T> getParameter(String parameterName, Type typeOfT) {
        if (parameterName == null || parameterName.isEmpty()) {
            return Mono.error(NullPointerException::new);
        }
        try {
            GetParameterRequest parameterRequest = GetParameterRequest.builder()
                    .name(parameterName)
                    .build();
            CompletableFuture<GetParameterResponse> response = client.getParameter(parameterRequest);
            Gson gson = new Gson();
            return Mono.just(gson.fromJson(response.join().parameter().value(), typeOfT));
        } catch (SsmException e) {
            log.info("Error fetching paramet    er from ParameterStore Service" + e.getMessage());
            return Mono.error(e);
        } catch (JsonParseException e) {
            log.info("Error parsing response to class: " + typeOfT.getTypeName() + " provided.");
            log.info(e.getMessage());
            return Mono.error(e);
        }
    }

    public <T> Mono<T> putParameter(String name, T instance) {
        if (instance == null || name == null) {
            return Mono.error(NullPointerException::new);
        }
        try {
            ParameterType parameterType = ParameterType.STRING;
            Gson gson = new Gson();
            String value = gson.toJson(instance, instance.getClass());
            PutParameterRequest request = PutParameterRequest.builder()
                    .name(name)
                    .value(value)
                    .dataType("String")
                    .type(parameterType)
                    .build();
            client.putParameter(request);
            return Mono.just(instance);
        } catch (JsonParseException e) {
            log.info("Error parsing response to class: " + instance.getClass() + " provided.");
            log.info(e.getMessage());
            return Mono.error(e);
        } catch (SdkException e) {
            log.info("Error on aws sdk. Exception: " + e.getMessage());
            return Mono.error(e);
        }
    }

}
