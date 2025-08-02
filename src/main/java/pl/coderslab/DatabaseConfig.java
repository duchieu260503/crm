//package pl.coderslab;
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
//public class DatabaseConfig {
//    // Cấu hình DataSource
//    @Bean
//    public DataSource dataSource() {
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setJdbcUrl("jdbc:mysql://192.168.197.112:3306/CRM?useSSL=false&allowPublicKeyRetrieval=true");
//        dataSource.setUsername("vcx_test");
//        dataSource.setPassword("Vcxtest@2025");
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setMaximumPoolSize(10);
//        dataSource.setMinimumIdle(5);
//        dataSource.setIdleTimeout(30000);
//        dataSource.setMaxLifetime(1800000);
//        dataSource.setConnectionTimeout(30000);
//        dataSource.setPoolName("SpringBootHikariPool");
//        return dataSource;
//    }
//
//    // Cấu hình EntityManagerFactory
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource());
//        em.setPackagesToScan("com.example.entity"); // Package chứa entity
//        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        em.setJpaProperties(hibernateProperties());
//        return em;
//    }
//
//    // Cấu hình Hibernate properties
//    private Properties hibernateProperties() {
//        Properties properties = new Properties();
////        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
//        properties.put("hibernate.hbm2ddl.auto", "update"); // update, create, none
//        properties.put("hibernate.show_sql", "true");
//        properties.put("hibernate.format_sql", "true");
//        return properties;
//    }
//
//    // Cấu hình Transaction Manager
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
//        return transactionManager;
//    }
//}
