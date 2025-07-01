package com.group7.swp391.drug_prevention.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.apache.catalina.Manager;

import java.time.Instant;
import java.util.List;

@Table(name = "tests")
@Entity
@Data
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double score;
    private String name;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "managerId")
    private User manager;

    @JsonIgnore
    @OneToMany(mappedBy = "test")
    private List<TestResult> testResults;
}
