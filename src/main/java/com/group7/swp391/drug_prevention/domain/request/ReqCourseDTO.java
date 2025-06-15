package com.group7.swp391.drug_prevention.domain.request;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReqCourseDTO {
    @Column(name = "name",columnDefinition = "VARCHAR(50)",nullable = false)
    private String name;
    @Column(name = "description",columnDefinition = "VARCHAR(250)",nullable = false)
    private String description;
    @Column(name = "video",columnDefinition = "VARCHAR(250)",nullable = false)
    private String video;
    @Column(name = "status",columnDefinition = "VARCHAR(10)",nullable = false)
    private String status;
    private long ageGroupId;

}
