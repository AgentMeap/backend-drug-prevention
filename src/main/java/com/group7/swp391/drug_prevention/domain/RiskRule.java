package com.group7.swp391.drug_prevention.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "RiskRule")
@Data
public class RiskRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double minScore;
    private double maxScore;

    @Column(name = "riskLevel",columnDefinition = "NVARCHAR(15)")
    private String riskLevel;

    @Column(name = "recommendations",columnDefinition = "NVARCHAR(250)")
    private String recommendations;

    @ManyToMany(mappedBy = "riskRule")
    private List<TestResult> TestResult;



    @Column(name = "ortherAction",columnDefinition = "NVARCHAR(250)")
    private String ortherAction; // để thể hiện các hành động như
    // tiêm chích để đánh giá mức độ dựa trên việc này//

    @Column(name = "action",columnDefinition = "NVARCHAR(40)")
    private String action; //trong phaần gợi ý để xuất ra màn hình là đặt lịch, đăng ký khoá học...

    @ManyToOne
    @JoinColumn(name = "testId")
    private Test test;
}
