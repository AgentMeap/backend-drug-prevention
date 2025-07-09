package com.group7.swp391.drug_prevention.repository;

import com.group7.swp391.drug_prevention.domain.TestChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestChoiceRepository extends JpaRepository<TestChoice,Long> {
    List<TestChoice> findByTestQuestion_Id(long questionId);
    List<TestChoice> findByTestQuestionIdBetween(long startTestQuestionId, long endTestQuestionId);

}
