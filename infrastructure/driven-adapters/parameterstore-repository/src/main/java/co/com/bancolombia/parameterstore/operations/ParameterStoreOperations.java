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
import java.util.concurrent.CompletableFuture;


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
            CompletableFuture<GetParameterResponse> response = client.getParameter(parameterRequest);
            String resString = response.join().parameter().value();
            return Mono.just(gson.fromJson(resString, typeOfT));
        } catch (SsmException e) {
            log.info("Error fetching parameter from ParameterStore Service" + e.getMessage());
            return null;
        } catch (JsonParseException e) {
            log.info("Error parsing response to class: " + typeOfT.getTypeName() + " provided.");
            log.info(e.getMessage());
            return null;
        }
    }

}
