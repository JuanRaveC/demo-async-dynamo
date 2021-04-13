package co.com.bancolombia.model.configmodel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ConfigModel {
    private String id;
    private String payload;
}
