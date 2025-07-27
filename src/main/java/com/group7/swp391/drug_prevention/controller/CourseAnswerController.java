package com.group7.swp391.drug_prevention.controller;

import com.group7.swp391.drug_prevention.domain.OnlineCourseAnswer;
import com.group7.swp391.drug_prevention.service.CourseAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/manager/online-course-answers")
@RequiredArgsConstructor
@PreAuthorize("hasRole('MANAGER')")
public class CourseAnswerController {

    private final CourseAnswerService answerService;

    /** List all answers */
    @GetMapping
    public ResponseEntity<List<OnlineCourseAnswer>> getAll() {
        return ResponseEntity.ok(answerService.getAllAnswers());
    }

    /** Get one answer by its ID */
    @GetMapping("/{id}")
    public ResponseEntity<OnlineCourseAnswer> getById(@PathVariable Long id) {
        return ResponseEntity.ok(answerService.getAnswerById(id));
    }

    /** (Optional) List answers for a given question */
    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<OnlineCourseAnswer>> getByQuestion(@PathVariable Long questionId) {
        return ResponseEntity.ok(answerService.getAnswersByQuestion(questionId));
    }

    /** Create a new answer */
    @PostMapping
    public ResponseEntity<OnlineCourseAnswer> create(@RequestBody OnlineCourseAnswer answer) {
        OnlineCourseAnswer created = answerService.createAnswer(answer);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /** Update an existing answer */
    @PutMapping("/{id}")
    public ResponseEntity<OnlineCourseAnswer> update(
            @PathVariable Long id,
            @RequestBody OnlineCourseAnswer answer
    ) {
        return ResponseEntity.ok(answerService.updateAnswer(id, answer));
    }

    /** Delete an answer */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        answerService.deleteAnswer(id);
        return ResponseEntity.noContent().build();
    }
}
