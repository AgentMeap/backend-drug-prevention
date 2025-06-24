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
    private int id;

    @Column(name = "description",columnDefinition = "NVARCHAR(100)")
    private String description;

    @Column(name = "liked",nullable = true)
    private int liked;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "blogId")
    private Blog blog;

}
