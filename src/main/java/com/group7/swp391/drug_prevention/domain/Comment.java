package com.group7.swp391.drug_prevention.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.group7.swp391.drug_prevention.util.constant.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Comment")
@Data
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(name = "[Like]")
    private int like;


    @ManyToOne
    @JoinColumn(name = "User_id")
    @JsonIgnoreProperties({"listEvents", "listBlogs", "listComments", "listSchedule", "listBooking", "bookedList", "listCourse", "listEventUsers", "listTests", "userFeedbacks", "password", "refreshToken"})
    private User user;

    @ManyToOne
    @JoinColumn(name = "Blog_id")
    @JsonIgnore // ✅ Ngắt vòng lặp khi Blog → Comment → Blog
    private Blog blog;
}