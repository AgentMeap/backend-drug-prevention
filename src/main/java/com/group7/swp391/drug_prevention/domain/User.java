package com.group7.swp391.drug_prevention.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

//    private Instant createdAt;
//    private Instant updatedAt;
}
