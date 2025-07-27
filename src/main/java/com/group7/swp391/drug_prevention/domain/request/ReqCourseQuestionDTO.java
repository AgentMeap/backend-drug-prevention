package com.group7.swp391.drug_prevention.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Payload for creating/updating an OnlineCourseQuestion
 */
@Data
public class ReqCourseQuestionDTO {
    @NotBlank(message = "Question text must not be blank")
    private String questionText;

    @NotNull(message = "onlineCourseId is required")
    private Long onlineCourseId;
}
