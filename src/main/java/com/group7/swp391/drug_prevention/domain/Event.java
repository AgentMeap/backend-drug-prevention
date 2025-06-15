package com.group7.swp391.drug_prevention.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "events")
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title",columnDefinition = "NVARCHAR(100)", nullable = false)
    private String title;
    private String description;
    private String location;
    @Column(name = "programCoordinator",columnDefinition = "NVARCHAR(100)", nullable = false)
    private String programCoordinator;
    private LocalTime startTime;
    private LocalTime endTime;

    private LocalTime createdAt;
    @Column(name = "updateAt",nullable = true)
    private LocalTime updatedAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "managerId")
    private User manager;

}
