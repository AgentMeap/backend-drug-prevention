package com.group7.swp391.drug_prevention.domain.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResEventDTO {
    private String title;
    private String description;
    private String location;
    @Column(name = "programCoordinator",columnDefinition = "NVARCHAR(100)", nullable = false)
    private String programCoordinator;
    private Instant startTime;
    private Instant endTime;
}
