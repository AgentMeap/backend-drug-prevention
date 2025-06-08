package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.repository.UserRepository;
import jakarta.validation.Valid;
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

    public List<User> fetchAllUser() {
        return this.userRepository.findAll();
    }

    public User handleUpdateUser(long id, User user) {
        User existingUser = fetchUserById(id);
        if (existingUser == null) {
            return null;
        }

        // Update the existing user's fields
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
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
