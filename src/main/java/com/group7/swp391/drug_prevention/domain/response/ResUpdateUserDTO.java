package com.group7.swp391.drug_prevention.domain.response;

import com.group7.swp391.drug_prevention.util.constant.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ResUpdateUserDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String dateOfBirth;
    private Instant updatedAt;
}
