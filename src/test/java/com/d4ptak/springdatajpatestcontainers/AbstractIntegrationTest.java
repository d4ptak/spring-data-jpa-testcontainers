package com.d4ptak.springdatajpatestcontainers;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

public abstract class AbstractIntegrationTest {

    private static final String IMAGE_VERSION = "postgres:11.9";

    static final PostgreSQLContainer POSTGRESQL_CONTAINER;

    static {
        POSTGRESQL_CONTAINER = new PostgreSQLContainer(IMAGE_VERSION);
        POSTGRESQL_CONTAINER.start();
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                "spring.datasource.url=" + POSTGRESQL_CONTAINER.getJdbcUrl(),
                "spring.datasource.username=" + POSTGRESQL_CONTAINER.getUsername(),
                "spring.datasource.password=" + POSTGRESQL_CONTAINER.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

}
