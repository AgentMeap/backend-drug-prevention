package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.*;
import com.group7.swp391.drug_prevention.domain.request.ReqAnswerDTO;
import com.group7.swp391.drug_prevention.domain.request.ReqTestChoiceDTO;
import com.group7.swp391.drug_prevention.domain.response.ResTestDetailDTO;
import com.group7.swp391.drug_prevention.repository.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestChoiceService {
    private final TestChoiceRepository testChoiceRepository;
    private final TestResultRepository testResultRepository;
    private final UserRepository userRepository;
    private final TestRepository testRepository;
    private final CategoryRepository categoryRepository;
    private final RiskRuleRepository riskRuleRepository;

    public TestChoiceService(TestChoiceRepository testChoiceRepository, TestResultRepository testResultRepository, UserRepository userRepository, TestRepository testRepository, CategoryRepository categoryRepository, RiskRuleRepository riskRuleRepository) {
        this.testChoiceRepository = testChoiceRepository;
        this.testResultRepository = testResultRepository;
        this.userRepository = userRepository;
        this.testRepository = testRepository;
        this.categoryRepository = categoryRepository;
        this.riskRuleRepository = riskRuleRepository;
    }

    public ResTestDetailDTO countScoreCrafftTests(ReqTestChoiceDTO dto) {

        long score = 0;

        TestResult testResult = new TestResult();
        User member = userRepository.findById(dto.getMemberId()).orElse(null);
        List<Test> tests = testRepository.findAllById(dto.getTestId());
        for (Test test : tests) {
            testResult.setTest(test);
        }
        for (ReqAnswerDTO answerDTO : dto.getAnswers()) {
            List<TestChoice> testChoice = testChoiceRepository.findByTestQuestion_Id(answerDTO.getQuestionId());
            for (TestChoice tc : testChoice) {
                if (answerDTO.getChoiceText().equals(tc.getChoiceText())) {
                    score += tc.getScore();
                }
            }
        }

        RiskRule riskRule = riskRuleRepository.findByScoreBetweenByTestId(score, dto.getTestId()).getFirst();

        testResult.setScore(score);
        testResult.setTakenAt(Instant.now());
        testResult.setMember(member);
        testResultRepository.save(testResult);

        ResTestDetailDTO response = new ResTestDetailDTO();
        response.setScore(score);
        response.setRiskLevel(riskRule.getRiskLevel());
        response.setRecommendations(riskRule.getRecommendations());
        response.setAction(riskRule.getAction());
        response.setOtherAction(riskRule.getOrtherAction());
        return response;
    }

    public ResTestDetailDTO countAssistTest(ReqTestChoiceDTO dto) {

        long score = 0;
        TestResult testResult = new TestResult();
        User member = userRepository.findById(dto.getMemberId()).orElse(null);
        Test test = testRepository.findById(dto.getTestId()).orElse(null);
        for (ReqAnswerDTO answerDTO : dto.getAnswers()) {
            List<TestChoice> testChoice = testChoiceRepository.findByTestQuestion_Id(answerDTO.getQuestionId());
            for (TestChoice tc : testChoice) {
                if (answerDTO.getChoiceText().equals(tc.getChoiceText())) {
                    score += tc.getScore();
                }
            }
        }

        testResult.setScore(score);
        testResult.setTakenAt(Instant.now());
        testResult.setMember(member);
        testResult.setTest(test);

        testResultRepository.save(testResult);
        RiskRule riskRule = riskRuleRepository.findByScoreBetweenByTestId(score, dto.getTestId()).getFirst();
        ResTestDetailDTO response = new ResTestDetailDTO();
        response.setScore(score);
        response.setRiskLevel(riskRule.getRiskLevel());
        response.setRecommendations(riskRule.getRecommendations());
        response.setAction(riskRule.getAction());
        response.setOtherAction(riskRule.getOrtherAction());
        return response;
    }

    public List<TestChoice> findAllTestChoicesCrafft() {
        List<TestChoice> crafftTestChoices = testChoiceRepository.findByTestQuestionIdBetween(1, 9);
        return crafftTestChoices;
    }

    public List<TestChoice> findAllTestChoiceAssist() {
        return testChoiceRepository.findByTestQuestionIdBetween(101, 109);
    }

}
