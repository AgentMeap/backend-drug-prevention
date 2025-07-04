package com.group7.swp391.drug_prevention.domain.response;

import lombok.Data;

import java.time.Instant;

@Data
public class ResEventDTO {
    private int id;
    private String title;
    private String description;
    private String location;
    private String imageUrl;
    private String programCoordinator;
    private Instant startDate;
    private Instant endDate;

    private Long managerId;
    private String managerName;
}