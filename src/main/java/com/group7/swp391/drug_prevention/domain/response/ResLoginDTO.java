package com.group7.swp391.drug_prevention.domain.response;

import com.group7.swp391.drug_prevention.util.constant.RoleEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
public class ResLoginDTO {
    private String accessToken;
    private UserLogin user;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserLogin{
        private long id;
        private String username;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String email;
        private String dateOfBirth;
        @Enumerated(EnumType.STRING)
        private RoleEnum role;

    }

}
