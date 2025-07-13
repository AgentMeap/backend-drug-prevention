package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.SubQuestion;
import com.group7.swp391.drug_prevention.domain.TestQuestion;
import com.group7.swp391.drug_prevention.repository.SubQuestionRepository;
import com.group7.swp391.drug_prevention.repository.TestQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestQuestionService {
    private TestQuestionRepository testQuestionRepository;
    private SubQuestionRepository subQuestionRepository;

    public TestQuestionService(TestQuestionRepository testQuestionRepository, SubQuestionRepository subQuestionRepository) {
        this.testQuestionRepository = testQuestionRepository;
        this.subQuestionRepository = subQuestionRepository;
    }

    public List<TestQuestion> getAll() {
        return testQuestionRepository.findAll();
    }

    public List<TestQuestion> findAllByCategoryId(long categoryId) {
        List<TestQuestion> testQuestions = testQuestionRepository.findAllByCategoryId(categoryId);
        return testQuestions;

    }

    public List<TestQuestion> findAllCrafftQuestions() {
        return testQuestionRepository.findAllByCategoryId(1);
    }

    public List<TestQuestion> findAllAssistQuestions() {
        List<TestQuestion> testQuestions = testQuestionRepository.findAllByCategoryId(2);

        testQuestions.addAll(testQuestionRepository.findAllByCategoryId(3));
        testQuestions = testQuestions.stream().map(testQuestion -> {
            List<SubQuestion> subQuestions = subQuestionRepository.findByTestQuestionId(testQuestion.getId());
            if (subQuestions != null) {
                testQuestion.setSubQuestion(subQuestions);
            }
            return testQuestion;
        }).toList();

        return testQuestions;
    }
}
