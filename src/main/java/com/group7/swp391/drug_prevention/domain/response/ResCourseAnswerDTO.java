package com.group7.swp391.drug_prevention.domain.response;

import lombok.Data;

@Data
public class ResCourseAnswerDTO {
    private Long id;
    private String answerText;
    private boolean isCorrect;
}
