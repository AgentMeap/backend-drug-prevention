package com.group7.swp391.drug_prevention.domain.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqFeedbackEventDTO {
    @Column(name = "comment",columnDefinition = "NVARCHAR(250)")
    private String comment;
    private long memberId;
    private long eventId;
}
