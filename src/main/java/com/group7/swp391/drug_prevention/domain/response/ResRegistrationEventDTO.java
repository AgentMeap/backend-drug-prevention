package com.group7.swp391.drug_prevention.domain.response;

import lombok.Data;

@Data
public class ResRegistrationEventDTO {
    private Long id;
    private Long eventId;
    private Long memberId;
    private String registeredAt;
    private String status;
}