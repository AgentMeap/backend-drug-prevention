package com.group7.swp391.drug_prevention.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResTestDetailDTO {
    private double score;
    private String riskLevel;
    private String action;
    private String otherAction;
    private String recommendations;
}
