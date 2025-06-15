package com.group7.swp391.drug_prevention.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name",columnDefinition = "VARCHAR(50)",nullable = false)
    private String name;
    @Column(name = "description",columnDefinition = "VARCHAR(250)",nullable = false)
    private String description;
    @Column(name = "video",columnDefinition = "VARCHAR(250)",nullable = false)
    private String video;
    @Column(name = "status",columnDefinition = "VARCHAR(10)",nullable = false)
    private String status;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime createdAt;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime updatedAt;

    @OneToOne
    @JoinColumn(name = "ageGroupId")
    @JsonIgnore
    private AgeGroup ageGroup;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private User member;
}
