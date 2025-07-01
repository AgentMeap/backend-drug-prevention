package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.Test;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.domain.request.ReqTestDTO;
import com.group7.swp391.drug_prevention.domain.response.ResTestDTO;
import com.group7.swp391.drug_prevention.repository.TestRepository;
import com.group7.swp391.drug_prevention.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestService {
    private TestRepository testRepository;
    private UserRepository userRepository;
    public TestService(TestRepository testRepository, UserRepository userRepository) {
        this.testRepository = testRepository;
        this.userRepository = userRepository;
    }


}
