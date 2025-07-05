package com.group7.swp391.drug_prevention.domain.request;

import com.group7.swp391.drug_prevention.domain.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqTestDTO {

    private String name;

    private long categoryId;
    private long managerId;
}


