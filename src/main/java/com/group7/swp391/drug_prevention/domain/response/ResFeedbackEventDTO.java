package com.group7.swp391.drug_prevention.domain.response;

import lombok.Data;

@Data
public class ResFeedbackEventDTO {
    private Integer id;
    private Integer eventId;
    private Long memberId;
    private Integer rating;
    private String comment;
}