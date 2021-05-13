package co.com.bancolombia.usecase.config;

import java.util.stream.Stream;

public class FileGenerationUseCase {

    public String wrapValue(String data, String delimiter) {
        if (data.contains(delimiter))
            data = "'" + data + "'";
        return data;
    }

    public String getObjectAsString(Object[] objects, String delimiter) {
        return Stream.of(objects)
                .map(this::getString)
                .reduce((accum, obj) -> accum.concat(delimiter).concat(obj))
                .orElse("");
    }

    public String getString(Object obj){
        return obj != null ? obj.toString() : "";
    }

    public String getString(Object obj, String delimiter){
        return obj != null ? wrapValue(obj.toString(), delimiter) : "";
    }
}
