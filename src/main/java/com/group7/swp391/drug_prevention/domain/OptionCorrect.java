package com.group7.swp391.drug_prevention.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "OptionCorrect")
public class OptionCorrect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "optionKeyCorrect",columnDefinition = "CHAR(1)", nullable = false)
    private String optionKey;

    @Column(name = "optionValueCorrect",columnDefinition = "NVARCHAR(250)", nullable = false)
    private String optionValue;

    @OneToOne(mappedBy = "optionCorrect")
    private Question question;
}
