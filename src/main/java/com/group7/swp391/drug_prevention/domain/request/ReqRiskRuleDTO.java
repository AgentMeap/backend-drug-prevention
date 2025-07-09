package com.group7.swp391.drug_prevention.domain.request;

import lombok.Data;

import java.util.List;

@Data
public class ReqRiskRuleDTO {
    private List<Long> testResultId;
}
