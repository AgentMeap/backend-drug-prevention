package com.group7.swp391.drug_prevention.repository;

import com.group7.swp391.drug_prevention.domain.SubQuestion;
import com.group7.swp391.drug_prevention.domain.TestChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubQuestionRepository extends JpaRepository<SubQuestion, Long> {
    List<SubQuestion> findByTestQuestionId(long questionId);
}
