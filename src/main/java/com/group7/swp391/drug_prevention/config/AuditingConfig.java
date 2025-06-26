package com.group7.swp391.drug_prevention.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditingConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || !authentication.isAuthenticated()) {
                return Optional.of("system");
            }

            String username = null;
            Object principal = authentication.getPrincipal();

            if (principal instanceof Jwt jwt) {
                username = jwt.getSubject();
            } else if (principal instanceof String) {
                username = (String) principal;
            } else {
                username = authentication.getName();
            }

            if ("anonymousUser".equals(username)) {
                return Optional.of("system");
            }

            return Optional.ofNullable(username);
        };
    }
}