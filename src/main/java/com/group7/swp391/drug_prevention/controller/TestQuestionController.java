package com.group7.swp391.drug_prevention.controller;

import com.group7.swp391.drug_prevention.domain.TestQuestion;
import com.group7.swp391.drug_prevention.service.TestQuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class TestQuestionController {
    private TestQuestionService testQuestionService;
    public TestQuestionController(TestQuestionService testQuestionService) {
        this.testQuestionService = testQuestionService;
    }

    @GetMapping("/findAllCrafftQuestions")
    public List<TestQuestion> findAllCrafftQuestion(){
        return testQuestionService.findAllCrafftQuestions();
    }

    @GetMapping("/findAllAssistQuestions")
    public List<TestQuestion> findAllAssistQuestion(){
        return testQuestionService.findAllAssistQuestions();
    }
}
