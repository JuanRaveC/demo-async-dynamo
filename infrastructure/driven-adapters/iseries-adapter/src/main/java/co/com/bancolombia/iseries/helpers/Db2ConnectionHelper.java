package co.com.bancolombia.iseries.helpers;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400JDBCDriver;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public class Db2ConnectionHelper {

     public Connection getDb2Connection(){
         try {


             AS400JDBCDriver driver = new AS400JDBCDriver();
             Properties properties = new Properties();
             properties.put("naming", "sql");
             properties.put("errors", "full");
             AS400 as400 = new AS400(host, userid, password);
             return driver.connect(as400, properties, schema);
         } catch (SQLException exception) {
             log.info("Error: ".concat(exception.getSQLState()));
             exception.printStackTrace();
             return null;
         }
     }

}
