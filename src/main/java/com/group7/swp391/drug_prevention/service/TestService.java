package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.Category;
import com.group7.swp391.drug_prevention.domain.Test;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.domain.request.ReqTestDTO;
import com.group7.swp391.drug_prevention.domain.response.ResTestDTO;
import com.group7.swp391.drug_prevention.repository.CategoryRepository;
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
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;
    public TestService(TestRepository testRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.testRepository = testRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    public Test createTest(ReqTestDTO dto) {
        Test test = new Test();
        Category category = categoryRepository.findById(dto.getCategoryId()).get();
        User manager = userRepository.findById(dto.getManagerId()).get();
        test.setCategory(category);
        test.setManager(manager);
        test.setName(dto.getName());
        testRepository.save(test);
        return test;

    }
}
