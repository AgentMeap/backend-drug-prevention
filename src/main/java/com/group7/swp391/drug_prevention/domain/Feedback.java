package com.group7.swp391.drug_prevention.domain;

import com.group7.swp391.drug_prevention.util.constant.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Table(name = "Feedback")
@Entity
@Data
public class Feedback extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private int rate;
    private String status; //hide,active,flagged

    @ManyToOne
    @JoinColumn(name = "member")
    private User member;

    @ManyToOne
    @JoinColumn(name = "course")
    private Course course;
}
