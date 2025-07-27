package com.group7.swp391.drug_prevention.repository;

import com.group7.swp391.drug_prevention.domain.OnlineCourseQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseQuestionRepository extends JpaRepository<OnlineCourseQuestion, Long> {
    List<OnlineCourseQuestion> findByOnlineCourseId(Long onlineCourseId);

    boolean existsByQuestionTextAndOnlineCourseId(String questionText, Long onlineCourseId);
}
