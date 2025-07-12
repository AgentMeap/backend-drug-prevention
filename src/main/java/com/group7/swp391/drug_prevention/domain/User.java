package com.group7.swp391.drug_prevention.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group7.swp391.drug_prevention.util.constant.BaseEntity;
import com.group7.swp391.drug_prevention.util.constant.RoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.util.List;


@Entity
@Table(name = "users")
@Data
@Getter
@Setter
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

    @Pattern(regexp = "^(0|\\+84)(3|5|7|8|9)([0-9]{8})$",
            message = "Số điện thoại không hợp lệ. Số điện thoại phải bắt đầu bằng 0 hoặc +84 và có 10 chữ số")
    private String phoneNumber;
    private String email;
    private String dateOfBirth;

    private String avatar;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Column(columnDefinition = "VARCHAR(MAX)")
    private String refreshToken;


    @JsonIgnore
    @OneToMany(mappedBy = "consultant", cascade = CascadeType.ALL)
    private List<Schedule> listSchedule;

    @JsonIgnore
    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Booking> listBooking;

    @JsonIgnore
    @OneToMany(mappedBy = "consultant", cascade = CascadeType.ALL)
    private List<Booking> bookedList;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<RegistrationCourse> listRegistrationCourse;

    @JsonIgnore
    @OneToMany(mappedBy = "manager",cascade = CascadeType.ALL)
    private List<Event> listEvents;

    @JsonIgnore
    @ManyToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Event> listEventUsers;

    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Comment> listComments;

    @JsonIgnore
    @OneToMany(mappedBy = "manager")
    private List<Blog> listBlogs;



    @OneToMany(mappedBy = "manager")
    @JsonIgnore
    private List<Test> listTestManager;

    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<UserAnswer> userAnswers;

    @OneToMany(mappedBy = "consultant")
    @JsonIgnore
    private List<Certificate>  listCertificate;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<FeedbackEvent> listFeedbackEvent;

    private String googleMeetLink;
}
