package com.group7.swp391.drug_prevention.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "blogs")
@Data
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title",columnDefinition = "NVARCHAR(100)")
    private String title;
    private String content;
    @Column(name = "type",columnDefinition = "NVARCHAR(100)")
    private String type;
    private LocalTime createdAt;
    private LocalTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "managerId")
    private User manager;

    @OneToMany(mappedBy = "blog")
    private List<Comment> listComments;
}
