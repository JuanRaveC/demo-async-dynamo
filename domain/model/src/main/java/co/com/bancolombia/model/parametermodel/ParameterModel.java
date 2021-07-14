package co.com.bancolombia.model.parametermodel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ParameterModel {
    private String name;
    private String type;
    private String category;
    private Security security;
    private String keyValue;
}
