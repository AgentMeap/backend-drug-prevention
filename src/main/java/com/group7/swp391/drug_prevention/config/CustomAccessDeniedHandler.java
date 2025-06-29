package com.group7.swp391.drug_prevention.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group7.swp391.drug_prevention.domain.response.RestResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper mapper;

    public CustomAccessDeniedHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=UTF-8");

        RestResponse<Object> res = new RestResponse<Object>();
        res.setStatusCode(HttpStatus.FORBIDDEN.value());

        String errorMessage = Optional.ofNullable(accessDeniedException.getCause())
                .map(Throwable::getMessage)
                .orElse(accessDeniedException.getMessage());
        res.setError(errorMessage);
        res.setMessage("Bạn không có quyền truy cập tài nguyên này.");

        mapper.writeValue(response.getWriter(), res);
    }
}
