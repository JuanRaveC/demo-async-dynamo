package co.com.bancolombia.usecase.config;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class FileGenerationUseCaseTest {

    @Test
    public void testGetObjectAsStringShouldBeOk(){
        Object[] object = {"Juan","Maria","Pedro","123456"};
        FileGenerationUseCase useCase = new FileGenerationUseCase();
        String delimiter = ";";
        String expected = "Juan;Maria;Pedro;123456";
        String response = useCase.getObjectAsString(object, delimiter);
        System.out.println(response);
        assertEquals(expected, response);
    }

    @Test
    public void testGetObjectAsStringShouldBeOkWithWrapValue(){
        Object[] object = {"Juan","Maria","Pedro","123.456"};
        FileGenerationUseCase useCase = new FileGenerationUseCase();
        String delimiter = ".";
        String expected = "Juan.Maria.Pedro.'123.456'";
        String response = useCase.getObjectAsString(object, delimiter);
        System.out.println(response);
        assertEquals(expected, response);
    }

    @Test
    public void testFilterGroups(){
        List<String> groups = new ArrayList<>();
        groups.add("A_MP_SKYNET_FUNCIONALES_DEV");
        groups.add("EveryOne - AmbientesBC");
        groups.stream()
                .filter(group -> group.contains("A_MP_SKYNET_"))
                .forEach(System.out::println);

    }

}