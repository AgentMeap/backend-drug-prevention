package com.group7.swp391.drug_prevention.controller;

import com.group7.swp391.drug_prevention.domain.OnlineCourseAnswer;
import com.group7.swp391.drug_prevention.domain.OnlineCourseQuestion;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.service.OnlineCourseAnswerService;
import com.group7.swp391.drug_prevention.service.OnlineCourseQuestionService;
import com.group7.swp391.drug_prevention.service.OnlineCourseQuestionService.AnswerSubmission;
import com.group7.swp391.drug_prevention.service.OnlineCourseQuestionService.SubmissionResult;
import com.group7.swp391.drug_prevention.service.UserService;
import com.group7.swp391.drug_prevention.util.error.IdInvalidException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OnlineCourseQuestionController {

    private final OnlineCourseQuestionService onlineCourseQuestionService;
    private final UserService userService;

    @GetMapping("/online-courses/{courseId}/questions")
    public ResponseEntity<List<OnlineCourseQuestion>> getQuestionsForCourse(@PathVariable Long courseId) {
        List<OnlineCourseQuestion> questions = onlineCourseQuestionService.getQuestionsByCourseId(courseId);
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/questions/{questionId}/answers")
    public ResponseEntity<List<OnlineCourseAnswer>> getAnswersForQuestion(@PathVariable Long questionId) throws IdInvalidException {
        List<OnlineCourseAnswer> answers = onlineCourseQuestionService.getAnswersByQuestionId(questionId);
        return ResponseEntity.ok(answers);
    }

    @PostMapping("online-courses/{courseId}/submit-answers")
    public ResponseEntity<?> submitAnswers(
            @PathVariable Long courseId,
            @RequestBody List<AnswerSubmission> submissions,
            Authentication authentication
    ) {
        String username = authentication.getName();
        User member = userService.handleGetUserByUsername(username);

        if (member == null) {
            return ResponseEntity.status(401).body("User not found for the provided token.");
        }

        // Pass the correct user object to the service.
        SubmissionResult result = onlineCourseQuestionService.submitAnswers(courseId, submissions, member);
        return ResponseEntity.ok(result);
    }
}