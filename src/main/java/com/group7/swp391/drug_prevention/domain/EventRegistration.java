package com.group7.swp391.drug_prevention.domain;

import jakarta.persistence.*;
import lombok.Data;
import com.group7.swp391.drug_prevention.domain.User;

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

    @Column(name = "event_id", nullable = false)
    private Integer eventId;
}