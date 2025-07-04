package com.group7.swp391.drug_prevention.domain;

import com.group7.swp391.drug_prevention.util.constant.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Table(name = "FeedbackCourse")
@Entity
@Data
public class FeedbackCourse extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private int rate;

    @Column(name = "status",columnDefinition = "NVARCHAR(250)",nullable = false)
    private String status; //hide,active,flagged


    @ManyToOne
    @JoinColumn(name = "courseId")
    private OnlineCourse onlineCourse;

    @ManyToOne
    @JoinColumn(name = "registrationId")
    private RegistrationCourse registrationCourse;

}
