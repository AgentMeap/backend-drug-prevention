package com.group7.swp391.drug_prevention.domain.response;

public class ResLoginDTO {
    private String accessToken;
    private String role; // ✅ thêm field role

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}