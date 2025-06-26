package com.group7.swp391.drug_prevention.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "OptionChoice")
public class OptionChoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "optionKey",columnDefinition = "CHAR", nullable = false)
    private String optionKey;

    @Column(name = "optionValue",columnDefinition = "NVARCHAR(250)", nullable = false)
    private String optionValue;

    @ManyToOne
    @JoinColumn(name = "questionId")
    private Question question;
}
