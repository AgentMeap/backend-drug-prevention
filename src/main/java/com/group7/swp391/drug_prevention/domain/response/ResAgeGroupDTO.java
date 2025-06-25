package com.group7.swp391.drug_prevention.domain.response;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ResAgeGroupDTO {
    @Column(name = "name",columnDefinition = "NVARCHAR(250)",nullable = false)
    private String name;
    private String age;
}
