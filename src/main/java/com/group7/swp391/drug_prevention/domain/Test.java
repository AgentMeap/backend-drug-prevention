package com.group7.swp391.drug_prevention.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Table(name = "tests")
@Entity
@Data
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "category",columnDefinition = "NVARCHAR(20)", nullable = false)
    private String category;
    private double score;
    @Column(name = "riskLevel",columnDefinition = "NVARCHAR(10)", nullable = false)
    private String riskLevel;

    private Instant createdAt; // ngày giờ member làm bài test//

    @ManyToOne
    @JoinColumn(name = "memberId")
    private User member;
}
