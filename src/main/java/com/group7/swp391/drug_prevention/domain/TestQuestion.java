package com.group7.swp391.drug_prevention.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group7.swp391.drug_prevention.util.constant.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "TestQuestion")
public class TestQuestion {
    @Id
    private long id;

    @Column(name = "questionText",columnDefinition = "NVARCHAR(200)")
    private String questionText;
    private String questionOrder;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @OneToMany(mappedBy = "testQuestion")
    @JsonIgnore
    private List<TestChoice> listTestChoice;

    @ManyToOne
    @JoinColumn(name = "subQuestionId")
    private SubQuestion subQuestion;


}
