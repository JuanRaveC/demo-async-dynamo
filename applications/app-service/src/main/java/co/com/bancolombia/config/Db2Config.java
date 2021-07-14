package co.com.bancolombia.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class Db2Config {

    @Value("${spring.jpa.databasePlatform}")
    String dialect;

    @Value("${spring.datasource.driverClassName}")
    String driverClass;

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.ibm.as400.access.AS400JDBCDriver");
        dataSourceBuilder.url("jdbc:as400://DDSET01;prompt=false");
        dataSourceBuilder.username("DDSTDCJRC");
        dataSourceBuilder.password("AWS4RCH1");
        return dataSourceBuilder.build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(this.dataSource());
        em.setPackagesToScan("co.com.bancolombia");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", dialect);
        em.setJpaProperties(properties);

        return em;
    }

}
