package com.group7.swp391.drug_prevention.domain.request;

import com.group7.swp391.drug_prevention.util.constant.RoleEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqUpdateUserRoleDTO {
    @NotNull(message = "Role không được để trống")
    private RoleEnum role;
}
