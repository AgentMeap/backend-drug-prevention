package com.group7.swp391.drug_prevention.controller;

import com.group7.swp391.drug_prevention.domain.request.ReqCourseQuestionDTO;
import com.group7.swp391.drug_prevention.domain.response.ResCourseQuestionDTO;
import com.group7.swp391.drug_prevention.service.CourseQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/online-course-questions")
@RequiredArgsConstructor
@PreAuthorize("hasRole('MANAGER')")
public class CourseQuestionController {

    private final CourseQuestionService questionService;

    @GetMapping
    public ResponseEntity<List<ResCourseQuestionDTO>> getAll() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResCourseQuestionDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.getQuestionById(id));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<ResCourseQuestionDTO>> getByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(questionService.getQuestionsByCourse(courseId));
    }

    @PostMapping
    public ResponseEntity<ResCourseQuestionDTO> create(
            @Valid @RequestBody ReqCourseQuestionDTO reqDto
    ) {
        ResCourseQuestionDTO created = questionService.createQuestion(reqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResCourseQuestionDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody ReqCourseQuestionDTO reqDto
    ) {
        return ResponseEntity.ok(questionService.updateQuestion(id, reqDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}
