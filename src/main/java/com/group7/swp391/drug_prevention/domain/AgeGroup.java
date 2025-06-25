package com.group7.swp391.drug_prevention.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group7.swp391.drug_prevention.util.constant.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "ageGroups")
@Data
public class AgeGroup extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name",columnDefinition = "NVARCHAR(250)",nullable = false)
    private String name;
    @Column(name = "maxAge",nullable = false)
    private int maxAge;
    @Column(name = "minAge",nullable = false)
    private int minAge;

    @OneToMany(mappedBy = "ageGroup")
    @JsonIgnore
    private List<Course> courses;
}
