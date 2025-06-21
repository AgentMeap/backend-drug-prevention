package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.domain.response.ResultPaginationDTO;
import com.group7.swp391.drug_prevention.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User handleCreateUser(User user) {
        return this.userRepository.save(user);
    }

    public User fetchUserById(long id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        return userOptional.orElse(null);
    }


    public void handleDeleteUser(long id) {
        this.userRepository.deleteById(id);
    }

    public ResultPaginationDTO fetchAllUser(Specification<User> spec, Pageable pageable) {
        Page<User> userPage = this.userRepository.findAll(spec, pageable);
        ResultPaginationDTO rs = new ResultPaginationDTO();
        ResultPaginationDTO.Meta mt = new ResultPaginationDTO.Meta();

        mt.setPage(userPage.getNumber()+1);
        mt.setPageSize(userPage.getSize());

        mt.setPages(userPage.getTotalPages());
        mt.setTotal(userPage.getTotalElements());

        rs.setMeta(mt);
        rs.setResult(userPage.getContent());
        return rs;
    }

    public User handleUpdateUser(long id, User user) {
        User existingUser = fetchUserById(id);
        if (existingUser == null) {
            return null;
        }

        // Update the existing user's fields
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setFullName(user.getFullName());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setEmail(user.getEmail());
        existingUser.setDateOfBirth(user.getDateOfBirth());
        existingUser.setRole(user.getRole());

        return userRepository.save(existingUser);
    }

    public User handleGetUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
}
