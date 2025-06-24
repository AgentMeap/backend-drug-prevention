package com.group7.swp391.drug_prevention.domain.request;

import com.group7.swp391.drug_prevention.util.constant.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqRegisterDTO {
    @NotBlank(message = "Username không được để trống")
    private String username;

    @NotBlank(message = "Password không được để trống")
    private String password;

    @NotBlank(message = "Tên không được để trống")
    private String firstName;

    @NotBlank(message = "Họ không được để trống")
    private String lastName;

    @NotNull(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^(0|\\+84)(3|5|7|8|9)([0-9]{8})$",
            message = "Số điện thoại không hợp lệ. Số điện thoại phải bắt đầu bằng 0 hoặc +84 và có 10 chữ số")
    private String phoneNumber;

    private String email;
    private String dateOfBirth;
    private RoleEnum role;
}