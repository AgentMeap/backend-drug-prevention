package com.group7.swp391.drug_prevention.domain.response;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ResRiskRuleDTO {
    @Column(name = "recommendation",columnDefinition = "NVARCHAR(200)")
    private String recommendation;
    private long score;
    @Column(name = "riskLevel",columnDefinition = "NVARCHAR(30)")
    private String riskLevel;
    @Column(name = "action",columnDefinition = "NVARCHAR(20)")
    private String action;
}
