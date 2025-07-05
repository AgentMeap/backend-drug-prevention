package com.group7.swp391.drug_prevention.repository;

import com.group7.swp391.drug_prevention.domain.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult,Long> {
    TestResult findByMemberId(long memberId);
}
