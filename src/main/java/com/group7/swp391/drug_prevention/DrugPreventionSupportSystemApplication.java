package com.group7.swp391.drug_prevention;

import com.group7.swp391.drug_prevention.domain.EventRegistration;
import com.group7.swp391.drug_prevention.domain.User;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.core.env.Environment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class DrugPreventionSupportSystemApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DrugPreventionSupportSystemApplication.class, args);

    }

}
