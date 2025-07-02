package com.group7.swp391.drug_prevention.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.group7.swp391.drug_prevention.util.SecurityUtil;
import com.group7.swp391.drug_prevention.util.constant.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity

@Data
@Table(name = "schedules")
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne

    @JoinColumn(name = "consultantId")
    private User consultant;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

    private LocalDate day;

    private Instant createdAt;

    private Instant updatedAt;

    private String createdBy;
    private String updatedBy;



}
