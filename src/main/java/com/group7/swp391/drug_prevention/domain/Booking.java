package com.group7.swp391.drug_prevention.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "bookings")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Instant bookingTime;
    @JsonFormat(pattern = "HH:mm:ss")
    private Instant createdAt;
    @JsonFormat(pattern = "HH:mm:ss")
    private Instant updatedAt;

    private String status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "memberId")
    private User member;

}
