package com.my.shop.myshop.configure;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource(value = "classpath:application.properties")
public class Datasource {

    @Value("${${evn}.datasource.driverClassName}")
    private String DRIVER;

    @Value ("${${evn}.datasource.url}")
    private String URL;

    @Value ("${${evn}.datasource.username}")
    private String USER_NAME;

    @Value ("${${evn}.datasource.password}")
    private String PASSWORD;

    @Value ("${${evn}.datasource.schema}")
    private String SCHEMA;


    private Logger log= LoggerFactory.getLogger(Datasource.class);
    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource factoryBean = new DriverManagerDataSource();
        factoryBean.setDriverClassName(DRIVER);
        factoryBean.setUrl(URL);
        factoryBean.setUsername(USER_NAME);
        factoryBean.setPassword(PASSWORD);
        factoryBean.setSchema(SCHEMA);

        log.info("=====>>>> Obtained database credentials from Vault Properties");
        log.info("=====>>>> driver:" + factoryBean);
        log.info("=====>>>> url:" + factoryBean.getUrl());
        log.info("=====>>>> username:" + factoryBean.getUsername());
        log.info("=====>>>> schema:" + factoryBean.getSchema());
        return factoryBean;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.springboot.starter.domain");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        transactionManager.setNestedTransactionAllowed(true);
        return transactionManager;
    }

    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        //properties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("hibernate.show_sql", "false");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.generate_statistics", "false");
        properties.setProperty("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
        properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
        properties.setProperty("hibernate.jdbc.lob.non_contextual_creation", "true");
        properties.setProperty("hibernate.default_schema",SCHEMA);
        return properties;
    }
}
