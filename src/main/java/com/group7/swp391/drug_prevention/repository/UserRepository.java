package com.group7.swp391.drug_prevention.repository;

import com.group7.swp391.drug_prevention.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User findByUsername(String username);

    Optional<User> findByEmail(String email);


    boolean existsByUsername(String username);

    List<User> getById(long id);
    User findByRefreshTokenAndUsername(String refreshToken, String username);

}
