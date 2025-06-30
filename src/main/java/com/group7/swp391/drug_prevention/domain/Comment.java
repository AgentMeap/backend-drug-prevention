package com.group7.swp391.drug_prevention.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group7.swp391.drug_prevention.util.constant.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "comments")
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
    @JoinColumn(name = "user_id")
    @JsonIgnore // ✅ Ngắt vòng lặp khi User → Comment → User
    private User user;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    @JsonIgnore // ✅ Ngắt vòng lặp khi Blog → Comment → Blog
    private Blog blog;
}