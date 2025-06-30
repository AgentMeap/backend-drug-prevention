package com.group7.swp391.drug_prevention.domain.response;


import com.group7.swp391.drug_prevention.domain.OnlineCourse;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResRegistrationDTO {
    @Column(name = "status",columnDefinition = "NVARCHAR(10)",nullable = false)
    private String status;
    private OnlineCourse course;
}
