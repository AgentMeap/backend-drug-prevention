package com.group7.swp391.drug_prevention.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqTestDTO {
    private String category;
    private double score;
    private String riskLevel;
    private Instant createdAt;
    private long memberId;
}


