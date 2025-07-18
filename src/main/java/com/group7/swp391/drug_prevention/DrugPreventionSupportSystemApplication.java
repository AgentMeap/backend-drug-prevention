package com.group7.swp391.drug_prevention;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.core.env.Environment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class DrugPreventionSupportSystemApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DrugPreventionSupportSystemApplication.class, args);

        // Dù context.getEnvironment() là ConfigurableEnvironment, bạn vẫn có thể dùng như Environment
        Environment env = context.getEnvironment();

        String username = env.getProperty("DB_USERNAME");
        String password = env.getProperty("DB_PASSWORD");

        System.out.println(">>> DB_USERNAME = " + username);
        System.out.println(">>> DB_PASSWORD = " + password);

    }

}
