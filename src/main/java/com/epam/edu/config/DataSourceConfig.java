package com.epam.edu.config;

import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DataSourceConfig {

  @Bean("DataSourceTest")
  @ConditionalOnMissingBean(name = "DataSource")
  @Profile("QA")
  public DataSource getDataSourceQA() {
    DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.driverClassName("org.h2.Driver");
    dataSourceBuilder.url("jdbc:h2:mem:in_memory");
    dataSourceBuilder.username("admin");
    dataSourceBuilder.password("admin");
    return dataSourceBuilder.build();
  }

  @Bean("DataSourceDev")
  @ConditionalOnMissingBean(name = "DataSource")
  @Profile("DEV")
  public DataSource getDataSourceDev() {
    DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.driverClassName("org.h2.Driver");
    dataSourceBuilder.url("jdbc:h2:mem:in_memory");
    dataSourceBuilder.username("admin");
    dataSourceBuilder.password("admin");
    return dataSourceBuilder.build();
  }

}
