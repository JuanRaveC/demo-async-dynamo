package co.com.bancolombia.dynamo.adapter;

import co.com.bancolombia.model.configmodel.ConfigModel;
import co.com.bancolombia.model.configmodel.gateways.ConfigRepository;
import org.springframework.stereotype.Repository;
import co.com.bancolombia.dynamo.operations.DynamoOperations;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class DynamoAdapter implements ConfigRepository {

   private final DynamoOperations operations;

   @Override
   public Mono<ConfigModel> getModel(String id) {
      return operations.getObject(id);
   }
}