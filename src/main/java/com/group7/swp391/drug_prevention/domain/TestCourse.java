package com.group7.swp391.drug_prevention.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.aspectj.weaver.patterns.TypePatternQuestions;

import java.util.List;

@Data
@Entity
@Table(name = "TestCourse")
public class TestCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Course course;

    @OneToMany(mappedBy = "testCourse")
    private List<Question> questions;
}
