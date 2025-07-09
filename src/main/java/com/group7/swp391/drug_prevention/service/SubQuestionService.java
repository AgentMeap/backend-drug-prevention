package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.SubQuestion;
import com.group7.swp391.drug_prevention.repository.SubQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubQuestionService {
    private final SubQuestionRepository subQuestionRepository;
    public SubQuestionService(SubQuestionRepository subQuestionRepository) {
        this.subQuestionRepository = subQuestionRepository;
    }

    public List<SubQuestion> findAll(){
        return subQuestionRepository.findAll();
    }
}
