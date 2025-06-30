package com.group7.swp391.drug_prevention.domain;

import com.group7.swp391.drug_prevention.util.constant.BaseEntity;
import com.group7.swp391.drug_prevention.util.constant.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "registrations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationCourse extends BaseEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    private Instant registrationDate;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private User member;

    @ManyToOne
    @JoinColumn(name = "onlineCouseId")
    private OnlineCourse onlineCourse;
}
