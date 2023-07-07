package com.clean.adm.catalog.infrastructure;

import com.clean.adm.catalog.infrastructure.configuration.WebServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {

//        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "development");
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "test");

        SpringApplication.run(WebServerConfig.class, args);
    }
}