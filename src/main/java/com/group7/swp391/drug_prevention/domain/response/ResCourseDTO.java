package com.group7.swp391.drug_prevention.domain.response;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalTime;

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


    public ResCourseDTO(String name, String description, String image, String videoUrl, int duration, long ageGroupId) {
        this.name = name;
        this.description = description;
        this.ageGroupId = ageGroupId;
        this.image = image;
        this.videoUrl = videoUrl;
        this.duration = duration;
    }
}
