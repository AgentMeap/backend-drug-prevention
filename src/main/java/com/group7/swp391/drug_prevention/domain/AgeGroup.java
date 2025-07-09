package com.group7.swp391.drug_prevention.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group7.swp391.drug_prevention.util.constant.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "age_groups")
@Data
public class AgeGroup extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name",columnDefinition = "NVARCHAR(250)",nullable = false)
    private String name;

    private String age;

    @OneToMany(mappedBy = "ageGroup")
    @JsonIgnore
    private List<OnlineCourse> course;
}
