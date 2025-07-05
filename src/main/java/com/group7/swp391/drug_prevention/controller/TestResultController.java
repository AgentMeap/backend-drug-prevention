package com.group7.swp391.drug_prevention.controller;

import com.group7.swp391.drug_prevention.domain.TestResult;
import com.group7.swp391.drug_prevention.domain.request.ReqTestChoiceDTO;
import com.group7.swp391.drug_prevention.service.TestChoiceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/testResult")
public class TestResultController {
    private final TestChoiceService testChoiceService;

    public TestResultController(TestChoiceService testChoiceService) {
        this.testChoiceService = testChoiceService;
    }

    @PostMapping("/countAssistTest/")
    public double countAssistTest(@RequestBody ReqTestChoiceDTO dto) {
        return testChoiceService.countScoreCrafftTests(dto);
    }
}
