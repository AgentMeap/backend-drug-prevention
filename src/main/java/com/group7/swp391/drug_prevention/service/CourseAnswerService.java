package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.OnlineCourseAnswer;
import com.group7.swp391.drug_prevention.repository.OnlineCourseAnswerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseAnswerService {

    private final OnlineCourseAnswerRepository answerRepository;

    @Transactional(readOnly = true)
    public List<OnlineCourseAnswer> getAllAnswers() {
        return answerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<OnlineCourseAnswer> getAnswersByQuestion(Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    @Transactional(readOnly = true)
    public OnlineCourseAnswer getAnswerById(Long id) {
        return answerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Answer not found with id " + id));
    }

    public OnlineCourseAnswer createAnswer(OnlineCourseAnswer answer) {
        answer.setId(0);
        return answerRepository.save(answer);
    }

    public OnlineCourseAnswer updateAnswer(Long id, OnlineCourseAnswer updated) {
        OnlineCourseAnswer existing = getAnswerById(id);
        existing.setAnswerText(updated.getAnswerText());
        existing.setCorrect(updated.isCorrect());
        existing.setQuestion(updated.getQuestion());
        return answerRepository.save(existing);
    }

    public void deleteAnswer(Long id) {
        answerRepository.delete(getAnswerById(id));
    }
}
