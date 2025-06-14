package com.group7.swp391.drug_prevention.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime bookingTime;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime createdAt;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime updatedAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "memberId")
    private User member;

}
