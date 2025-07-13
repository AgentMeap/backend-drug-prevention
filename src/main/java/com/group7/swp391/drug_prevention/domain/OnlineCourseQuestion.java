package com.group7.swp391.drug_prevention.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group7.swp391.drug_prevention.util.constant.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "online_course_question")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnlineCourseQuestion extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String questionText;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "onlineCourseId", nullable = false)
    private OnlineCourse onlineCourse;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OnlineCourseAnswer> answers = new ArrayList<>();

}
