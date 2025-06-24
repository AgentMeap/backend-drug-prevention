package com.group7.swp391.drug_prevention.domain.request;

import lombok.Data;

@Data
public class ReqFeedbackDTO {
    private String comment;
    private int rate;
    private String status;
    private long memberId;
    private long courseId;

}
