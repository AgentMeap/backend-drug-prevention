package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.RiskRule;
import com.group7.swp391.drug_prevention.domain.TestResult;
import com.group7.swp391.drug_prevention.domain.request.ReqRiskRuleDTO;
import com.group7.swp391.drug_prevention.domain.response.ResRiskRuleDTO;
import com.group7.swp391.drug_prevention.repository.RiskRuleRepository;
import com.group7.swp391.drug_prevention.repository.TestResultRepository;
import org.springframework.stereotype.Service;

@Service
public class RiskRuleService {
    private final  RiskRuleRepository riskRuleRepository;
    private final TestResultRepository testResultRepository;
    public RiskRuleService(RiskRuleRepository riskRuleRepository, TestResultRepository testResultRepository) {
        this.riskRuleRepository = riskRuleRepository;
        this.testResultRepository = testResultRepository;
    }

    public ResRiskRuleDTO recommendRiskRule(ReqRiskRuleDTO dto) {
        TestResult testResult = testResultRepository.findById(dto.getTestResultId()).orElse(null);
        RiskRule riskRule = riskRuleRepository.findByScoreBetween(testResult.getScore());
        ResRiskRuleDTO resRiskRuleDTO = new ResRiskRuleDTO();
        resRiskRuleDTO.setRecommendation(riskRule.getRecommendations());
        resRiskRuleDTO.setScore(testResult.getScore());
        resRiskRuleDTO.setAction(riskRule.getAction());
        resRiskRuleDTO.setRiskLevel(riskRule.getRiskLevel());

        return resRiskRuleDTO;
    }
}
