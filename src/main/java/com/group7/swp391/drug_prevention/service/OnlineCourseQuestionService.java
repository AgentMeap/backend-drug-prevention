package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.*;
import com.group7.swp391.drug_prevention.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OnlineCourseQuestionService {

    // You might need to inject the specific repository here
    private final CourseRepository onlineCourseRepository;
    private final OnlineCourseQuestionRepository questionRepository;
    private final OnlineCourseAnswerRepository answerRepository;
    private final UserAnswerRepository userAnswerRepository;

    @Transactional
    public SubmissionResult submitAnswers(Long courseId, List<AnswerSubmission> submissions, User member) {
        OnlineCourse course = onlineCourseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        int correct = 0;
        int total = submissions.size();

        // This delete operation now runs inside a transaction
        userAnswerRepository.deleteByMemberIdAndOnlineCourseId(member.getId(), courseId);

        for (AnswerSubmission submission : submissions) {
            OnlineCourseQuestion question = questionRepository.findById(submission.getQuestionId())
                    .orElseThrow(() -> new RuntimeException("Question not found"));
            OnlineCourseAnswer selectedAnswer = answerRepository.findById(submission.getSelectedAnswerId())
                    .orElseThrow(() -> new RuntimeException("Answer not found"));

            if (selectedAnswer.isCorrect()) {
                correct++;
            }

            // Save user's answer
            UserAnswer userAnswer = new UserAnswer();
            userAnswer.setMember(member);
            userAnswer.setOnlineCourse(course);
            userAnswer.setQuestion(question);
            userAnswer.setSelectedAnswer(selectedAnswer);
            userAnswerRepository.save(userAnswer);
        }

        boolean passed = correct >= 4; // 80% of 5 is 4
        return new SubmissionResult(correct, total, passed);
    }

    // ... your inner classes AnswerSubmission and SubmissionResult
    public static class AnswerSubmission {
        private Long questionId;
        private Long selectedAnswerId;
        // Getters and setters
        public Long getQuestionId() { return questionId; }
        public void setQuestionId(Long questionId) { this.questionId = questionId; }
        public Long getSelectedAnswerId() { return selectedAnswerId; }
        public void setSelectedAnswerId(Long selectedAnswerId) { this.selectedAnswerId = selectedAnswerId; }
    }

    public static class SubmissionResult {
        private final int correct;
        private final int total;
        private final boolean passed;
        public SubmissionResult(int correct, int total, boolean passed) {
            this.correct = correct;
            this.total = total;
            this.passed = passed;
        }
        // Getters
        public int getCorrect() { return correct; }
        public int getTotal() { return total; }
        public boolean isPassed() { return passed; }
    }
}