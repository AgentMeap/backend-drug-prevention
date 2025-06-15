package com.group7.swp391.drug_prevention.domain.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReqBlogDTO {
    private String title;
    private String content;
    private String type;
    private long managerId;

}
