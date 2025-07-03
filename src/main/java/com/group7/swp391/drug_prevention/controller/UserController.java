package com.group7.swp391.drug_prevention.controller;

import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.domain.request.ReqChangePasswordDTO;
import com.group7.swp391.drug_prevention.domain.request.ReqUpdateUserDTO;
import com.group7.swp391.drug_prevention.domain.request.ReqUpdateUserRoleDTO;
import com.group7.swp391.drug_prevention.domain.response.*;
import com.group7.swp391.drug_prevention.service.UserService;
import com.group7.swp391.drug_prevention.util.annotation.ApiMessage;
import com.group7.swp391.drug_prevention.util.error.IdInvalidException;
import com.turkraft.springfilter.boot.Filter;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/users")
    @ApiMessage("create a new user")
    public ResponseEntity<ResCreateUserDTO> createNewUser(@Valid @RequestBody User user) throws IdInvalidException {
        boolean isUsernameExist = this.userService.isUsernameExist(user.getUsername());
        if(isUsernameExist) {
            throw new IdInvalidException(
                    "Username " + user.getUsername() + " đã tồn tại, vui lòng sử dụng tên khác."
            );
        }
        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        User createdUser = this.userService.handleCreateUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.convertToResCreateUserDTO(createdUser));
    }

    //fetch all user
    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CONSULTANT') or hasRole('MANAGER')")
    @ApiMessage("fetch all users")
    public ResponseEntity<ResultPaginationDTO> getAllUser(
            @Filter Specification<User> spec,
            Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK).body(this.userService.fetchAllUser(spec, pageable));
    }

    //fetch user by id
    @GetMapping("/users/{id}")
    public ResponseEntity<ResUserDTO> getUserById(@PathVariable("id") long id) throws IdInvalidException {
        User fetchUser = this.userService.fetchUserById(id);
        if(fetchUser == null) {
            throw new IdInvalidException("Không tìm thấy người dùng với ID: " + id);
        }
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.convertToResUserDTO(fetchUser));
    }

    //delete user by id
    @DeleteMapping("/users/{id}")
    @ApiMessage("Delete a user")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) throws IdInvalidException {
        User currentUser = this.userService.fetchUserById(id);
        if(currentUser == null) {
            throw new IdInvalidException("Không tìm thấy người dùng với ID: " + id);
        }
        this.userService.handleDeleteUser(id);
        return ResponseEntity.ok(null);
    }

    //update a user
    @PatchMapping("/users/{id}")
    @ApiMessage("Update a user")
    public ResponseEntity<ResUpdateUserDTO> updateUser(@PathVariable("id") long id, @Valid @RequestBody ReqUpdateUserDTO updateDTO
    ) throws IdInvalidException {
        User updatedUser = userService.handleUpdateUser(id, updateDTO);
        if (updatedUser == null) {
            throw new IdInvalidException("Không tìm thấy người dùng với ID: " + id);
        }
        return ResponseEntity.ok(this.userService.convertToResUpdateUserDTO(updatedUser));
    }
    @PostMapping("/users/{id}/change-password")
    @ApiMessage("Change user password")
    public ResponseEntity<ResChangePasswordDTO> changePassword(
            @PathVariable("id") long id,
            @Valid @RequestBody ReqChangePasswordDTO changePasswordDTO) throws IdInvalidException {
        User user = userService.fetchUserById(id);
        if (user == null) {
            throw new IdInvalidException("Không tìm thấy người dùng với ID: " + id);
        }

        if (!passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())) {
            throw new IdInvalidException("Mật khẩu cũ không chính xác.");
        }

        String hashedNewPassword = passwordEncoder.encode(changePasswordDTO.getNewPassword());
        user.setPassword(hashedNewPassword);
        userService.handleUpdateUserPassword(user);

        ResChangePasswordDTO response = new ResChangePasswordDTO(
                "Mật khẩu đã được thay đổi thành công.",
                "success",
                id
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/users/{id}/role")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiMessage("Update user role")
    public ResponseEntity<ResUpdateUserRoleDTO> updateUserRole(
            @PathVariable("id") long id,
            @Valid @RequestBody ReqUpdateUserRoleDTO updateRoleDTO) throws IdInvalidException {

        User user = userService.fetchUserById(id);
        if (user == null) {
            throw new IdInvalidException("Không tìm thấy người dùng với ID: " + id);
        }

        User updatedUser = userService.handleUpdateUserRole(id, updateRoleDTO.getRole());

        ResUpdateUserRoleDTO response = userService.convertToResUpdateUserRoleDTO(updatedUser);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}