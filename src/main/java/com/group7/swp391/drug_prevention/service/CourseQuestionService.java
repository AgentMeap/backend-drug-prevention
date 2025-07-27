package com.group7.swp391.drug_prevention.service;
import com.group7.swp391.drug_prevention.domain.OnlineCourse;
import com.group7.swp391.drug_prevention.domain.OnlineCourseQuestion;
import com.group7.swp391.drug_prevention.domain.request.ReqCourseQuestionDTO;
import com.group7.swp391.drug_prevention.domain.response.ResCourseAnswerDTO;
import com.group7.swp391.drug_prevention.domain.response.ResCourseQuestionDTO;

import com.group7.swp391.drug_prevention.repository.CourseRepository;
import com.group7.swp391.drug_prevention.repository.OnlineCourseQuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseQuestionService {

    private final OnlineCourseQuestionRepository questionRepo;
    private final CourseRepository courseRepo;

    @Transactional(readOnly = true)
    public List<ResCourseQuestionDTO> getAllQuestions() {
        return questionRepo.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ResCourseQuestionDTO> getQuestionsByCourse(Long courseId) {
        return questionRepo.findByOnlineCourseId(courseId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ResCourseQuestionDTO getQuestionById(Long id) {
        OnlineCourseQuestion q = questionRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id " + id));
        return mapToDTO(q);
    }

    public ResCourseQuestionDTO createQuestion(ReqCourseQuestionDTO req) {
        if (questionRepo.existsByQuestionTextAndOnlineCourseId(
                req.getQuestionText().trim(),
                req.getOnlineCourseId()
        )) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A question with that text already exists in this course"
            );
        }

        OnlineCourse course = courseRepo.findById(req.getOnlineCourseId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Course not found with id " + req.getOnlineCourseId()
                ));

        OnlineCourseQuestion q = new OnlineCourseQuestion();
        q.setQuestionText(req.getQuestionText());
        q.setOnlineCourse(course);

        OnlineCourseQuestion saved = questionRepo.save(q);
        return mapToDTO(saved);
    }

    public ResCourseQuestionDTO updateQuestion(Long id, ReqCourseQuestionDTO req) {
        OnlineCourseQuestion existing = questionRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id " + id));

        if (!existing.getQuestionText().equals(req.getQuestionText().trim())
                && questionRepo.existsByQuestionTextAndOnlineCourseId(
                req.getQuestionText().trim(),
                req.getOnlineCourseId()
        )
        ) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A question with that text already exists in this course"
            );
        }

        OnlineCourse course = courseRepo.findById(req.getOnlineCourseId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Course not found with id " + req.getOnlineCourseId()
                ));

        existing.setQuestionText(req.getQuestionText());
        existing.setOnlineCourse(course);

        OnlineCourseQuestion updated = questionRepo.save(existing);
        return mapToDTO(updated);
    }

    public void deleteQuestion(Long id) {
        OnlineCourseQuestion existing = questionRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id " + id));
        questionRepo.delete(existing);
    }

    private ResCourseQuestionDTO mapToDTO(OnlineCourseQuestion q) {
        ResCourseQuestionDTO dto = new ResCourseQuestionDTO();
        dto.setId(q.getId());
        dto.setQuestionText(q.getQuestionText());
        dto.setOnlineCourseId(q.getOnlineCourse().getId());
        dto.setOnlineCourseName(q.getOnlineCourse().getName());
        dto.setCreatedAt(q.getCreatedAt());
        dto.setUpdatedAt(q.getUpdatedAt());
        dto.setCreatedBy(q.getCreatedBy());
        dto.setUpdatedBy(q.getUpdatedBy());

        List<ResCourseAnswerDTO> answers = q.getAnswers()
                .stream()
                .map(a -> {
                    ResCourseAnswerDTO ad = new ResCourseAnswerDTO();
                    ad.setId(a.getId());
                    ad.setAnswerText(a.getAnswerText());
                    ad.setCorrect(a.isCorrect());
                    return ad;
                })
                .collect(Collectors.toList());

        dto.setAnswers(answers);
        return dto;
    }
}
