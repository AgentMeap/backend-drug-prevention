package com.group7.swp391.drug_prevention.repository;

import com.group7.swp391.drug_prevention.domain.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    void deleteByMemberIdAndOnlineCourseId(Long memberId, Long onlineCourseId);

}
