package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.TestQuestion;
import com.group7.swp391.drug_prevention.repository.TestQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestQuestionService {
    private TestQuestionRepository testQuestionRepository;
    public TestQuestionService(TestQuestionRepository testQuestionRepository) {
        this.testQuestionRepository = testQuestionRepository;
    }

    public List<TestQuestion> getAll(){
        return testQuestionRepository.findAll();
    }

}
