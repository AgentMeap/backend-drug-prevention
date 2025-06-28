package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.Course;
import com.group7.swp391.drug_prevention.domain.Feedback;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.domain.request.ReqFeedbackDTO;
import com.group7.swp391.drug_prevention.domain.response.ResFeedbackDTO;
import com.group7.swp391.drug_prevention.repository.CourseRepository;
import com.group7.swp391.drug_prevention.repository.FeedbackRepository;
import com.group7.swp391.drug_prevention.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public FeedbackService(FeedbackRepository feedbackRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.feedbackRepository = feedbackRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public List<Feedback> findAll() {
        return feedbackRepository.findAll();
    }

    public Feedback create(ReqFeedbackDTO dto) {
        User member = userRepository.findById(dto.getMemberId()).orElse(null);
        Course course = courseRepository.findById(dto.getCourseId()).orElse(null);

        Feedback feedback = new Feedback();

        feedback.setComment(dto.getComment());
        feedback.setRate(dto.getRate());
        feedback.setStatus(dto.getStatus());

        feedback.setMember(member);
        feedback.setCourse(course);

        feedbackRepository.save(feedback);
        return feedback;

    }

    public List<ResFeedbackDTO> findByMemberId(Long memberId) {
        List<Feedback> feedbacks = feedbackRepository.findByMemberId(memberId);
        List<ResFeedbackDTO> list = feedbacks.stream().map(feedback -> new ResFeedbackDTO(
                feedback.getComment(),
                feedback.getRate(),
                feedback.getStatus(),
                feedback.getMember().getUsername())).toList();
        return list;
    }


}
