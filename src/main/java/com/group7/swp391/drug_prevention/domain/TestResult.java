package com.group7.swp391.drug_prevention.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
@Table(name = "TestResult")
public class TestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double score;
    private Instant takenAt;

    @ManyToOne
    @JoinColumn(name = "testId")
    private Test test;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private User member;

    @OneToOne
    private RiskRule riskRule;
}
