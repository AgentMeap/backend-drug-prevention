package com.group7.swp391.drug_prevention.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group7.swp391.drug_prevention.util.constant.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(name = "description",columnDefinition = "NVARCHAR(100)")
    private String description;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Test> listTests;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<TestQuestion> listTestQuestions;

}
