package com.group7.swp391.drug_prevention.repository;

import com.group7.swp391.drug_prevention.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
     List<Test> getAllTestsByMemberId(Long memberId);
}
