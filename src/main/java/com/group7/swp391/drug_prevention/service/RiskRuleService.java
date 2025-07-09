package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.RiskRule;
import com.group7.swp391.drug_prevention.domain.TestResult;
import com.group7.swp391.drug_prevention.domain.request.ReqRiskRuleDTO;
import com.group7.swp391.drug_prevention.domain.response.ResRiskRuleDTO;
import com.group7.swp391.drug_prevention.repository.RiskRuleRepository;
import com.group7.swp391.drug_prevention.repository.TestResultRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RiskRuleService {
    private final  RiskRuleRepository riskRuleRepository;
    private final TestResultRepository testResultRepository;
    public RiskRuleService(RiskRuleRepository riskRuleRepository, TestResultRepository testResultRepository) {
        this.riskRuleRepository = riskRuleRepository;
        this.testResultRepository = testResultRepository;
    }

    public List<ResRiskRuleDTO> recommendRiskRule(ReqRiskRuleDTO dto) {
        List<ResRiskRuleDTO> resRiskRuleDTOList = new ArrayList<>();
        List<TestResult> testResult = testResultRepository.findAllById(dto.getTestResultId());
        for (TestResult testResult1 : testResult) {
            List<RiskRule> riskRule = riskRuleRepository.findByScoreBetween(testResult1.getScore());
            for(RiskRule riskRule1 : riskRule) {
                ResRiskRuleDTO resRiskRuleDTO = new ResRiskRuleDTO();
                resRiskRuleDTO.setRecommendation(riskRule1.getRecommendations());
                resRiskRuleDTO.setScore(testResult1.getScore());
                resRiskRuleDTO.setAction(riskRule1.getAction());
                resRiskRuleDTO.setRiskLevel(riskRule1.getRiskLevel());
                resRiskRuleDTOList.add(resRiskRuleDTO);

            }

        }
        return resRiskRuleDTOList;
    }
}
