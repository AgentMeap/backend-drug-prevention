package com.group7.swp391.drug_prevention.domain.response;

import com.group7.swp391.drug_prevention.util.constant.RoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResUpdateUserRoleDTO {
    private long userId;
    private String username;
    private RoleEnum role;

    public ResUpdateUserRoleDTO(long userId, String username, RoleEnum role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }
}
