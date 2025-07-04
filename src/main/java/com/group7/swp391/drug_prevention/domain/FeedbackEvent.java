package com.group7.swp391.drug_prevention.domain;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "FeedbackEvent")
@Data
public class FeedbackEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int rating;
    @Column(name = "comment",columnDefinition = "NVARCHAR(250)")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "eventId")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private User member;
}
