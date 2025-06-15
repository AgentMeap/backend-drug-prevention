package com.group7.swp391.drug_prevention.domain.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReqEventDTO {
    private String title;
    private long managerId;
    private String description;
    private String location;
    @Column(name = "programCoordinator",columnDefinition = "NVARCHAR(100)", nullable = false)
    private String programCoordinator;
    private LocalTime startTime;
    private LocalTime endTime;
}
