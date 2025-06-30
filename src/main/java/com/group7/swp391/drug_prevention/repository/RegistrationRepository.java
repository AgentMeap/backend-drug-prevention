package com.group7.swp391.drug_prevention.repository;

import com.group7.swp391.drug_prevention.domain.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByMemberId(Long memberId);
}
