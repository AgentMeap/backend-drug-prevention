package com.group7.swp391.drug_prevention.repository;

import com.group7.swp391.drug_prevention.domain.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
    List<Feedback> findByMemberId(Long memberId);
}
