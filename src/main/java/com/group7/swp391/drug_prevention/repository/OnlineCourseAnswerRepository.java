package com.group7.swp391.drug_prevention.repository;

import com.group7.swp391.drug_prevention.domain.OnlineCourseAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OnlineCourseAnswerRepository extends JpaRepository<OnlineCourseAnswer,Long> {
    List<OnlineCourseAnswer> findByQuestionId(Long questionId);
}
