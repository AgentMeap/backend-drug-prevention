package com.group7.swp391.drug_prevention.domain.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResCourseDTO {
    private String name;
    private String description;
    private long ageGroupId;
    private String image;
    private String videoUrl;
    private int duration;


}
