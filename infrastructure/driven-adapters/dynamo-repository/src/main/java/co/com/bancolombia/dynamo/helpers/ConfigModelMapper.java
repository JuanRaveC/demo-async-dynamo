package co.com.bancolombia.dynamo.helpers;

import co.com.bancolombia.model.configmodel.ConfigModel;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

@Slf4j
public class ConfigModelMapper {

    public static ConfigModel toModel(Map<String, AttributeValue> item){
        String id = item.get("id").s();
        log.info("id -> " + id);
        return ConfigModel.builder()
                .id(id)
                .build();
    }
}
