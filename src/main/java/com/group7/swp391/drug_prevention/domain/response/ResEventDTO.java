package com.group7.swp391.drug_prevention.domain.response;

import lombok.Data;

@Data
public class ResEventDTO {
    private int id;
    private String title;
    private String description;
    private String location;
    private String imageUrl;
    private String programCoordinator;
    private String startDate;
    private String endDate;
    private String createdAt;
    private String updatedAt;
    private Long managerId;
    private String managerName;
}