package com.group7.swp391.drug_prevention.domain;

import com.group7.swp391.drug_prevention.util.constant.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ConsultationSession")
public class ConsultationSession extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private String description;

    @ManyToOne
    @JoinColumn(name = "scheduleId")
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "bookingId")
    private Booking booking;
}
