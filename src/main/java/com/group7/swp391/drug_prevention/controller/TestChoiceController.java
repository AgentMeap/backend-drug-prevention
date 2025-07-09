package com.group7.swp391.drug_prevention.controller;

import com.group7.swp391.drug_prevention.domain.TestChoice;
import com.group7.swp391.drug_prevention.service.TestChoiceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class TestChoiceController {
    private TestChoiceService testChoiceService;
    public TestChoiceController(TestChoiceService testChoiceService) {
        this.testChoiceService = testChoiceService;
    }

    @GetMapping("/getCrafftTestChoice")
    public List<TestChoice> getCrafftTestChoice() {
        return testChoiceService.findAllTestChoicesCrafft();
    }

    @GetMapping("/getAssistTestChoice")
    public List<TestChoice> getAssistTestChoice() {
        return testChoiceService.findAllTestChoiceAssist();
    }
}
