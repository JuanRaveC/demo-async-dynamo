package co.com.bancolombia.dynamo.operations;

import co.com.bancolombia.dynamo.helpers.ConfigModelMapper;
import co.com.bancolombia.model.configmodel.ConfigModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;

import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class DynamoOperations {

    private final DynamoDbAsyncClient client;

    public Mono<ConfigModel> getObject(String id) {
        HashMap<String, AttributeValue> keyToGet = new HashMap<>();
        keyToGet.put("id", AttributeValue.builder()
                .s(id).build());

        GetItemRequest request = GetItemRequest.builder()
                .key(keyToGet)
                .tableName("hello-test-table")
                .build();

        return Mono.fromFuture(client.getItem(request))
                .switchIfEmpty(Mono.error(new Exception("Not Found "+ id )))//mejorar manejo de errores
                .map(getItemResponse -> ConfigModelMapper.toModel(getItemResponse.item())).cache();

    }
}
