package com.humansofulmu.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class DataSourceConfig {

    @Autowired
    private ParameterStoreConfig parameterStoreConfig;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(parameterStoreConfig.getParam("datasource-url"));
        dataSource.setUsername(parameterStoreConfig.getParam("datasource-username"));
        dataSource.setPassword(parameterStoreConfig.getParam("datasource-password"));

        return dataSource;
    }
}
