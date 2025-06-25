package com.group7.swp391.drug_prevention.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Table(name = "Test")
@Entity
@Data
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String category;
    private double score;
    private String riskLevel;
    private Instant createdAt; // ngày giờ member làm bài test//

    @ManyToOne
    @JoinColumn(name = "memberId")
    private User member;
}
