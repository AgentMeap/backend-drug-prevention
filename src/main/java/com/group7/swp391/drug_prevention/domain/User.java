package com.group7.swp391.drug_prevention.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.aspectj.weaver.Member;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;
import java.util.List;


@Entity
@Table(name = "users")
@Data

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;


    private String username;
    private String password;

    @Nationalized
    private String firstName;

    @Nationalized
    private String lastName;

    private String phoneNumber;
    private String email;
    private String dateOfBirth;
    private String role;

    @JsonIgnore
    @OneToMany(mappedBy = "consultant", cascade = CascadeType.ALL)
    private List<Schedule> listSchedule;

    @JsonIgnore
    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Booking> listBooking;

    @JsonIgnore
    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Course> listCourse;

    @JsonIgnore
    @OneToMany(mappedBy = "manager",cascade = CascadeType.ALL)
    private List<Event> listEvents;

    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Comment> listComments;

    @JsonIgnore
    @OneToMany(mappedBy = "manager")
    private List<Blog> listBlogs;
}
