package com.group7.swp391.drug_prevention.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group7.swp391.drug_prevention.util.constant.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.aspectj.weaver.patterns.TypePatternQuestions;

import java.util.List;

@Entity
@Data
@Table(name = "SubQuestion")
public class SubQuestion{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "[key]", nullable = false)
    private char key;
    @Column(name = "text",columnDefinition = "NVARCHAR(200)")
    private String text;

    @ManyToOne
    @JoinColumn(name = "testQuestionId")
    @JsonIgnore
    private TestQuestion testQuestion;
}
