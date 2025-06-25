package com.group7.swp391.drug_prevention.domain.response;

import com.group7.swp391.drug_prevention.domain.AgeGroup;
import com.group7.swp391.drug_prevention.domain.Course;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

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
