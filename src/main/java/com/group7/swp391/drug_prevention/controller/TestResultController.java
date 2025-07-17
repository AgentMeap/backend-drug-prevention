package com.group7.swp391.drug_prevention.controller;

import com.group7.swp391.drug_prevention.domain.TestResult;
import com.group7.swp391.drug_prevention.domain.request.ReqTestChoiceDTO;
import com.group7.swp391.drug_prevention.domain.response.ResTestDetailDTO;
import com.group7.swp391.drug_prevention.service.TestChoiceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/testResult")
public class TestResultController {
    private final TestChoiceService testChoiceService;
    private final RiskRuleController riskRuleController;

    public TestResultController(TestChoiceService testChoiceService, RiskRuleController riskRuleController) {
        this.testChoiceService = testChoiceService;
        this.riskRuleController = riskRuleController;
    }

    @PostMapping("/countCrafftTest/")
    public ResTestDetailDTO countCrafftTest(@RequestBody ReqTestChoiceDTO dto) {
        return testChoiceService.countScoreCrafftTests(dto);
    }

    @PostMapping("/countAssistTest/")
    public ResTestDetailDTO countAssistTest(@RequestBody ReqTestChoiceDTO dto) {
        return testChoiceService.countAssistTest(dto);
    }
}
