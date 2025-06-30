package com.group7.swp391.drug_prevention.domain;

import com.group7.swp391.drug_prevention.util.constant.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Data
@Table(name = "registration_course")
public class RegistrationCourse extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private Instant RegistrationDate;
    @Column(name = "status",columnDefinition = "NVARCHAR(20)",nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private User member;

    @ManyToOne
    @JoinColumn(name = "onlineCourseId")
    private OnlineCourse onlineCourse;

    @OneToMany(mappedBy = "registrationCourse")
    private List<FeedbackCourse> feedbackCourse;
}
