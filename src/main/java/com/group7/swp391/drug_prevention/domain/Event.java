package com.group7.swp391.drug_prevention.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group7.swp391.drug_prevention.util.constant.BaseEntity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "events")
@Data
public class Event extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title",columnDefinition = "NVARCHAR(100)", nullable = false)
    private String title;
    @Column(name = "description",columnDefinition = "NVARCHAR(250)", nullable = false)
    private String description;
    @Column(name = "location",columnDefinition = "NVARCHAR(50)", nullable = false)
    private String location;
    @Column(name = "programCoordinator",columnDefinition = "NVARCHAR(100)", nullable = false)
    private String programCoordinator;

    private Instant startTime;
    private Instant endTime;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "managerId")
    private User manager;

    @JsonIgnore
    @ManyToMany
    private List<User> member;

}
