package com.group7.swp391.drug_prevention.domain.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResChangePasswordDTO {
    private String message;
    private String status;
    private long userId;

    public ResChangePasswordDTO(String message, String status, long userId) {
        this.message = message;
        this.status = status;
        this.userId = userId;
    }
}
