package com.group7.swp391.drug_prevention.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.group7.swp391.drug_prevention.util.constant.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Entity
@Table(name = "Blog")
@Data
public class Blog extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Title", columnDefinition = "NVARCHAR(100)")
    private String title;

    @Column(name = "Content", columnDefinition = "NVARCHAR(MAX)")
    private String content;

    @Column(name = "Type", columnDefinition = "NVARCHAR(50)")
    private String type;

    @Column(name = "image_url", columnDefinition = "NVARCHAR(255)")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "Manager_id")
    @JsonIgnoreProperties({"listEvents", "listBlogs", "listComments", "listSchedule", "listBooking", "bookedList", "listCourse", "listEventUsers", "listTests", "userFeedbacks", "password", "refreshToken"})
    private User manager;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    @JsonIgnore // ✅ Ngắt vòng lặp khi Blog → Comment → Blog
    private List<Comment> comments;
}