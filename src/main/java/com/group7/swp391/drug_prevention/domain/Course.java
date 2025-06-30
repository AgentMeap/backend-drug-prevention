package com.group7.swp391.drug_prevention.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group7.swp391.drug_prevention.util.constant.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name",columnDefinition = "NVARCHAR(50)",nullable = false)
    private String name;
    @Column(name = "description",columnDefinition = "NVARCHAR(250)",nullable = false)
    private String description;
    @Column(name = "image",columnDefinition = "VARCHAR(250)",nullable = false)
    private String image;
    @Column(name = "video",columnDefinition = "VARCHAR(250)",nullable = false)
    private String videoUrl;
    @Column(name = "status",columnDefinition = "NVARCHAR(10)",nullable = false)
    private String status;
    private int duration;
    private Instant createdAt;
    private Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "ageGroupId")
    private AgeGroup ageGroup;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<Registration> listRegistration;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<Feedback>  feedbacks;
}
