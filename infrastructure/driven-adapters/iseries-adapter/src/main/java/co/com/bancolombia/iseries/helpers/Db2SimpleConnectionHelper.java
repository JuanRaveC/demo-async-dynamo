package co.com.bancolombia.iseries.helpers;


import com.ibm.as400.access.AS400JDBCConnectionHandle;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ibm.as400.access.AS400JDBCManagedConnectionPoolDataSource;
import com.ibm.as400.access.AS400JDBCManagedDataSource;

public class Db2SimpleConnectionHelper {

    public Connection getDb2DataSource()
            throws NamingException, SQLException {

        String host = "DDSET01";
        String userid = "DDSTDCJRC";
        String password = "Z2OMVXJH";
        String dbName = "DDSET01";


        AS400JDBCManagedConnectionPoolDataSource connectionPool = new AS400JDBCManagedConnectionPoolDataSource();
        // Set general datasource properties.  Note that both connection pool datasource  and managed
        // datasource (MDS) have these properties, and they might have different values.
        connectionPool.setServerName(host);
        connectionPool.setDatabaseName(dbName);//iasp can be here
        connectionPool.setUser(userid);
        connectionPool.setPassword(password);

        connectionPool.setSavePasswordWhenSerialized(true);

        // Set connection pooling-specific properties.
        int initialPoolSize = 2;
        int minPoolSize = 1;
        int maxPoolSize = 10;
        connectionPool.setInitialPoolSize(initialPoolSize);
        connectionPool.setMinPoolSize(minPoolSize);
        connectionPool.setMaxPoolSize(maxPoolSize);
//        connectionPool.setMaxLifetime((int) (maxLifetime_ / 1000));  // convert to seconds
//        connectionPool.setMaxIdleTime((int) (maxIdleTime_ / 1000));  // convert to seconds
//        connectionPool.setPropertyCycle((int) (propertyCycle_ / 1000));  // convert to seconds
        //connectionPool.setReuseConnections(false);  // do not re-use connections

        // Set the initial context factory to use.
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.messaging.mq.fscontext.RefFSContextFactory");
        Context ctx = new InitialContext();

        ctx.rebind("mydatasource", connectionPool);  // We can now do lookups on cpds, by the name "mydatasource".

        // Create a standard DataSource object that references it.
        AS400JDBCManagedDataSource mds0 = new AS400JDBCManagedDataSource();
        mds0.setDescription("DataSource supporting connection pooling");
        mds0.setDataSourceName("mydatasource");

        ctx.rebind("ConnectionPoolingDataSource", mds0);

        DataSource dataSource = (DataSource) ctx.lookup("ConnectionPoolingDataSource");

        AS400JDBCManagedDataSource managedDataSource = (AS400JDBCManagedDataSource) dataSource;

        boolean isHealthy = managedDataSource.checkPoolHealth(false);  //check pool health

        return dataSource.getConnection();
    }

}