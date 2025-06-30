package com.group7.swp391.drug_prevention.domain;

import com.group7.swp391.drug_prevention.util.constant.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Data
@Table(name = "Registration")
public class Registration extends BaseEntity {
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
    @JoinColumn(name = "courseId")
    private Course course;
}
