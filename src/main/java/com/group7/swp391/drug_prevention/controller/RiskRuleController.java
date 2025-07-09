package com.group7.swp391.drug_prevention.controller;

import com.group7.swp391.drug_prevention.domain.request.ReqRiskRuleDTO;
import com.group7.swp391.drug_prevention.domain.response.ResRiskRuleDTO;
import com.group7.swp391.drug_prevention.service.RiskRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/riskRule")
public class RiskRuleController {
    private RiskRuleService riskRuleService;
    public RiskRuleController(RiskRuleService riskRuleService) {
        this.riskRuleService = riskRuleService;
    }

    @PostMapping("/recommentRiskRule")
    public List<ResRiskRuleDTO> recommendRiskRule(@RequestBody ReqRiskRuleDTO dto) {
        return riskRuleService.recommendRiskRule(dto);
    }
}
