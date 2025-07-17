package com.group7.swp391.drug_prevention.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.aspectj.weaver.patterns.TypePatternQuestions;

@Entity
@Data
@Table(name = "TestChoice")
public class TestChoice {
    @Id
    private long id;

    @Column(name = "choiceText",columnDefinition = "NVARCHAR(200)")
    private String choiceText;
    private double score;

    @ManyToOne
    @JoinColumn(name = "questionId")
    private TestQuestion testQuestion;
}
