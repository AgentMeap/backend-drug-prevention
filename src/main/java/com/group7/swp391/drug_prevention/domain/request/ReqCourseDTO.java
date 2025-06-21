package com.group7.swp391.drug_prevention.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class ReqCourseDTO {
    @Column(name = "name",columnDefinition = "VARCHAR(50)",nullable = false)
    private String name;
    @Column(name = "description",columnDefinition = "VARCHAR(250)",nullable = false)
    private String description;
    @Column(name = "image",columnDefinition = "VARCHAR(250)",nullable = false)
    private String image;
    @Column(name = "video",columnDefinition = "VARCHAR(250)",nullable = false)
    private String videoUrl;
    @Column(name = "status",columnDefinition = "VARCHAR(10)",nullable = false)
    private String status;
    private int duration;
    private long ageGroupId;

}
