package co.com.bancolombia.iseries.operations;

import co.com.bancolombia.iseries.helpers.Db2ConnectionHelper;
import co.com.bancolombia.iseries.helpers.Db2SimpleConnectionHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IseriesOperationsTest {

    @Test
    void executeDb2Query() {
        Db2ConnectionHelper connectionHelper = new Db2ConnectionHelper();
        IseriesOperations operations = new IseriesOperations(connectionHelper);
        String response = operations.executeDb2Query("test");
        System.out.println(response);
    }

}