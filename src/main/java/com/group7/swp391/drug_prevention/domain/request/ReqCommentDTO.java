package com.group7.swp391.drug_prevention.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReqCommentDTO {
    private String description;
    private long blogId;
    private long userId;

}
