package com.group7.swp391.drug_prevention.repository;

import com.group7.swp391.drug_prevention.domain.SubQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface SubQuestionRepository extends JpaRepository<SubQuestion, Long> {
}
