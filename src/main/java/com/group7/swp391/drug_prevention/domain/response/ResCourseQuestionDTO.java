package com.group7.swp391.drug_prevention.domain.response;

import lombok.Data;

import java.time.Instant;
import java.util.List;


@Data
public class ResCourseQuestionDTO {
    private Long id;
    private String questionText;

    private Long onlineCourseId;
    private String onlineCourseName;

    private Instant createdAt;
    private Instant updatedAt;
    private String createdBy;
    private String updatedBy;

    private List<ResCourseAnswerDTO> answers;
}
