package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.AgeGroup;
import com.group7.swp391.drug_prevention.domain.response.ResAgeGroupDTO;
import com.group7.swp391.drug_prevention.repository.AgeGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgeGroupService {
    private AgeGroupRepository ageGroupRepository;
    public AgeGroupService(AgeGroupRepository ageGroupRepository) {
        this.ageGroupRepository = ageGroupRepository;
    }

}
