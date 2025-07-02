package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.domain.request.ReqUpdateUserDTO;
import com.group7.swp391.drug_prevention.domain.response.ResCreateUserDTO;
import com.group7.swp391.drug_prevention.domain.response.ResUpdateUserDTO;
import com.group7.swp391.drug_prevention.domain.response.ResUserDTO;
import com.group7.swp391.drug_prevention.domain.response.ResultPaginationDTO;
import com.group7.swp391.drug_prevention.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Page<User> pageUser = this.userRepository.findAll(spec, pageable);
        ResultPaginationDTO rs = new ResultPaginationDTO();
        ResultPaginationDTO.Meta mt = new ResultPaginationDTO.Meta();

        mt.setPage(pageable.getPageNumber()+1);
        mt.setPageSize(pageable.getPageSize());

        mt.setPages(pageUser.getTotalPages());
        mt.setTotal(pageUser.getTotalElements());

        rs.setMeta(mt);
        rs.setResult(pageUser.getContent());

        List<ResUserDTO> listUser = pageUser.getContent().stream().map(item -> new ResUserDTO(
                item.getId(),
                item.getUsername(),
                item.getFirstName(),
                item.getLastName(),
                item.getPhoneNumber(),
                item.getEmail(),
                item.getDateOfBirth(),
                item.getRole(),
                item.getCreatedAt(),
                item.getUpdatedAt())).collect(Collectors.toList());
        rs.setResult(listUser);
        return rs;
    }

    public User handleUpdateUser(long id, ReqUpdateUserDTO updateDTO) {
        User existingUser = fetchUserById(id);
        if(existingUser != null) {
            existingUser.setFirstName(updateDTO.getFirstName());
            existingUser.setLastName(updateDTO.getLastName());
            existingUser.setAvatar(updateDTO.getAvatar());
            existingUser.setPhoneNumber(updateDTO.getPhoneNumber());
            existingUser.setEmail(updateDTO.getEmail());
            existingUser.setDateOfBirth(updateDTO.getDateOfBirth());

            // update
            existingUser = this.userRepository.save(existingUser);
        }
        return existingUser;
    }

    public User handleGetUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public boolean isUsernameExist(String username) {
        return this.userRepository.existsByUsername(username);
    }

    public ResCreateUserDTO convertToResCreateUserDTO(User createdUser) {
        ResCreateUserDTO res = new ResCreateUserDTO();
        res.setId(createdUser.getId());
        res.setUsername(createdUser.getUsername());
        res.setFirstName(createdUser.getFirstName());
        res.setLastName(createdUser.getLastName());
        res.setPhoneNumber(createdUser.getPhoneNumber());
        res.setEmail(createdUser.getEmail());
        res.setDateOfBirth(createdUser.getDateOfBirth());
        res.setRole(createdUser.getRole());
        res.setCreatedAt(createdUser.getCreatedAt());
        return res;
    }


    public ResUserDTO convertToResUserDTO(User fetchUser) {
        ResUserDTO res = new ResUserDTO();
        res.setId(fetchUser.getId());
        res.setUsername(fetchUser.getUsername());
        res.setFirstName(fetchUser.getFirstName());
        res.setLastName(fetchUser.getLastName());
        res.setPhoneNumber(fetchUser.getPhoneNumber());
        res.setEmail(fetchUser.getEmail());
        res.setDateOfBirth(fetchUser.getDateOfBirth());
        res.setRole(fetchUser.getRole());
        res.setCreatedAt(fetchUser.getCreatedAt());
        res.setUpdatedAt(fetchUser.getUpdatedAt());
        return res;
    }

    public ResUpdateUserDTO convertToResUpdateUserDTO(User updatedUser) {
        ResUpdateUserDTO res = new ResUpdateUserDTO();
        res.setId(updatedUser.getId());
        res.setFirstName(updatedUser.getFirstName());
        res.setLastName(updatedUser.getLastName());
        res.setPhoneNumber(updatedUser.getPhoneNumber());
        res.setAvatar(updatedUser.getAvatar());
        res.setEmail(updatedUser.getEmail());
        res.setDateOfBirth(updatedUser.getDateOfBirth());
        return res;
    }

    public void updateUserToken(String token, String username){
        User currentUser = this.userRepository.findByUsername(username);
        if(currentUser != null){
            currentUser.setRefreshToken(token);
            this.userRepository.save(currentUser);
        }
    }

    public User getUserByRefreshTokenAndUsername(String refreshToken, String username) {
        return this.userRepository.findByRefreshTokenAndUsername(refreshToken, username);
    }

    public void handleUpdateUserPassword(User user) {
        User existingUser = fetchUserById(user.getId());
        if (existingUser != null) {
            existingUser.setPassword(user.getPassword());
            this.userRepository.save(existingUser);
        }
    }
}
