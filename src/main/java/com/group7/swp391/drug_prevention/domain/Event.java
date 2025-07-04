package com.group7.swp391.drug_prevention.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group7.swp391.drug_prevention.util.constant.BaseEntity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.time.LocalTime;
import java.util.List;
import com.group7.swp391.drug_prevention.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Event")
@Data
public class Event extends  BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(columnDefinition = "NVARCHAR(255)")
    private String title;

    @Column(name = "Program_Coordinator", columnDefinition = "NVARCHAR(255)")
    private String programCoordinator;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String description;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String location;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "Start_date")
    private Instant startDate;

    @Column(name = "End_date")
    private Instant endDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "managerId")
    private User manager;

    @JsonIgnore
    @ManyToMany
    private List<User> member;

    @JsonIgnore
    @OneToMany(mappedBy = "event")
    private List<FeedbackEvent> feedbackEvents;


    @JsonIgnore
    @OneToMany(mappedBy = "event")
    private List<EventRegistration> eventRegistrations;
}
