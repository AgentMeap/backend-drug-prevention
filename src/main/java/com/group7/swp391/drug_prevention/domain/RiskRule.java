package com.group7.swp391.drug_prevention.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "RiskRule")
public class RiskRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double minScore;
    private double maxScore;
    private String riskLevel;
    @Column(name = "recommendations",columnDefinition = "NVARCHAR(250)")
    private String recommendations;

    @OneToOne(mappedBy = "riskRule")
    private TestResult testResult;
}
