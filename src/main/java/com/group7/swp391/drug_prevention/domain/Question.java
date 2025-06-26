package com.group7.swp391.drug_prevention.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String questions;

    @OneToOne
    private OptionCorrect optionCorrect;

    @OneToMany(mappedBy = "question")
    private List<OptionChoice> optionChoices;

    @ManyToOne
    @JoinColumn(name = "testCourseId")
    private TestCourse testCourse;
}
