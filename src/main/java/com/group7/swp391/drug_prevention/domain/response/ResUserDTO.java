package com.group7.swp391.drug_prevention.domain.response;

import com.group7.swp391.drug_prevention.util.constant.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResUserDTO {
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String dateOfBirth;
    private RoleEnum role;
    private Instant createdAt;
    private Instant updatedAt;
}
