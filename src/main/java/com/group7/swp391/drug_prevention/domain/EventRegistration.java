package com.group7.swp391.drug_prevention.domain;

import jakarta.persistence.*;
import lombok.Data;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.util.constant.EventRegistrationStatus;

@Data
@Entity
@Table(name = "event_registrations")
public class EventRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private User member;

    @Column(name = "eventId", nullable = false) // <-- phải đúng tên cột trong DB
    private Integer eventId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "NVARCHAR(20)", nullable = false)
    private EventRegistrationStatus status;
}