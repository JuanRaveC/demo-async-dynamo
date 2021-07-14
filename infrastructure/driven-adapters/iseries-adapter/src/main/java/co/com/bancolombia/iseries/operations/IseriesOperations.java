package co.com.bancolombia.iseries.operations;

import co.com.bancolombia.iseries.helpers.Db2ConnectionHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Stream;

@Slf4j
@AllArgsConstructor
public class IseriesOperations {

    private final Db2ConnectionHelper connectionHelper;

    public String executeDb2Query(String name) {
        try {
            Connection connection = connectionHelper.getDb2Connection();
            //String response = connection.nativeSQL("Select * from EABLIBPR.EABFFHPOSM");

            ResultSet response;
            try (PreparedStatement statement = connection.prepareStatement("Select * from EABLIBPR.EABFFHPOSM")) {
                response = statement.executeQuery();
            }
            Stream.Builder streamData = Stream.builder();
            while (response.next()) {
                streamData.add(response.getObject(0));
            }
            Stream stream = streamData.build();
            stream.forEach(System.out::println);

            connection.close();
        } catch (SQLException exception) {
            log.info("Error".concat(exception.getMessage()));
        }
        return "Executed";
    }
}
