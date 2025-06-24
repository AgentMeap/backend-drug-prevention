package com.group7.swp391.drug_prevention.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group7.swp391.drug_prevention.util.constant.BaseEntity;
import com.group7.swp391.drug_prevention.util.constant.RoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.util.List;


@Entity
@Table(name = "users")
@Data

public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotBlank(message = "Username không được để trống")
    private String username;
    @NotBlank(message = "Password không được để trống")
    private String password;

    @Nationalized
    @NotBlank(message = "Tên không được để trống")
    private String firstName;

    @Nationalized
    @NotBlank(message = "Họ không được để trống")
    private String lastName;

    private String phoneNumber;
    private String email;
    private String dateOfBirth;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;


    private String refreshToken;


    @JsonIgnore
    @OneToMany(mappedBy = "consultant", cascade = CascadeType.ALL)
    private List<Schedule> listSchedule;

    @JsonIgnore
    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Booking> listBooking;

    @JsonIgnore
    @ManyToMany(mappedBy = "member",cascade = CascadeType.ALL)
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
