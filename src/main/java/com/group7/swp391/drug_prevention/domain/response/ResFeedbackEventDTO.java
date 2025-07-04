package com.group7.swp391.drug_prevention.domain.response;

import com.group7.swp391.drug_prevention.domain.Event;
import com.group7.swp391.drug_prevention.domain.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResFeedbackEventDTO {
    private int rating;
    @Column(name = "comment",columnDefinition = "NVARCHAR(250)")
    private String comment;
    private User member;
    private Event event;
}
