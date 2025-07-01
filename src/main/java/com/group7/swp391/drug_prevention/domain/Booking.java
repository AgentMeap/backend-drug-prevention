package com.group7.swp391.drug_prevention.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group7.swp391.drug_prevention.util.constant.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;


@Entity
@Table(name = "bookings")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Booking extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Instant bookingTime;
    private Instant createdAt;
    private Instant updatedAt;

    @Column(name = "status",columnDefinition = "NVARCHAR(250)",nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private User member;

    @ManyToOne
    @JoinColumn(name = "consultantId")
    private User consultant;

    @Column(name = "note",columnDefinition = "NVARCHAR(250)")
    private String note;

    @OneToMany(mappedBy = "booking")
    @JsonIgnore
    private List<ConsultationSession> listOfConsultationSessions;
}
