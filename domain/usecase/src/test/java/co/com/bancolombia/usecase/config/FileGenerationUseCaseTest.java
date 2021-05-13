package co.com.bancolombia.usecase.config;

import org.junit.jupiter.api.Test;

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

}