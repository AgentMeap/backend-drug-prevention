package com.group7.swp391.drug_prevention.domain;


import jakarta.persistence.*;

import com.group7.swp391.drug_prevention.domain.User;

@Entity
@Table(name = "Feedback_Event")
public class FeedbackEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Member_id")
    private User member;

    @Column(name = "Event_id")
    private int eventId;

    private int rating;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String comment;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getMember() {
        return member;
    }

    public void setMember(User member) {
        this.member = member;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}