package com.group7.swp391.drug_prevention.domain.request;

import lombok.Data;

import java.util.List;

@Data
public class ReqTestChoiceDTO {
    private long memberId;
    private long testId;
    private List<ReqAnswerDTO> answers;
}

