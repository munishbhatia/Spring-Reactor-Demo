package com.oanda.learning.springreactordemo;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

import javax.annotation.PreDestroy;

@SpringBootApplication
@EnableR2dbcRepositories
@EnableSwagger2WebFlux
@Configuration
public class SpringReactorDemoApplication extends AbstractR2dbcConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(SpringReactorDemoApplication.class, args);
    }

//    @Bean
//    public DatabaseClient getDatabaseClient() {
//        TransactionalDatabaseClient databaseClient = TransactionalDatabaseClient.create(connectionFactory());
//        return databaseClient;
//    }

    @PreDestroy
    void shutdown(){

    }

    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        PostgresqlConnectionConfiguration connectionConfiguration = PostgresqlConnectionConfiguration.builder()
                .host("localhost")
                .port(5432)
                .database("articledb")
                .username("postgres")
                .password("password")
                .build();
        return new PostgresqlConnectionFactory(connectionConfiguration);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}