package com.group7.swp391.drug_prevention.domain;

import com.group7.swp391.drug_prevention.util.constant.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "blogs")
@Data
public class Blog extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title",columnDefinition = "NVARCHAR(100)")
    private String title;
    @Column(name = "content",columnDefinition = "NVARCHAR(100)")
    private String content;
    @Column(name = "type",columnDefinition = "NVARCHAR(100)")
    private String type;


    @ManyToOne
    @JoinColumn(name = "managerId")
    private User manager;

    @OneToMany(mappedBy = "blog")
    private List<Comment> listComments;
}
