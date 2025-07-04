package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.OnlineCourse;
import com.group7.swp391.drug_prevention.domain.FeedbackCourse;
import com.group7.swp391.drug_prevention.domain.RegistrationCourse;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.domain.request.ReqFeedbackDTO;
import com.group7.swp391.drug_prevention.domain.response.ResFeedbackDTO;
import com.group7.swp391.drug_prevention.repository.CourseRepository;
import com.group7.swp391.drug_prevention.repository.FeedbackRepository;
import com.group7.swp391.drug_prevention.repository.RegistrationRepository;
import com.group7.swp391.drug_prevention.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final CourseRepository courseRepository;
    private final RegistrationRepository registrationRepository;

    public FeedbackService(FeedbackRepository feedbackRepository, UserRepository userRepository, CourseRepository courseRepository, RegistrationRepository registrationRepository) {
        this.feedbackRepository = feedbackRepository;
        this.courseRepository = courseRepository;
        this.registrationRepository = registrationRepository;
    }

    public List<FeedbackCourse> findAll() {
        return feedbackRepository.findAll();
    }

    public FeedbackCourse create(ReqFeedbackDTO dto) {
        RegistrationCourse registrationCourse = registrationRepository.getById(dto.getRegistrationCourseId());
        OnlineCourse onlineCourse = courseRepository.findById(dto.getCourseId()).orElse(null);

        FeedbackCourse feedbackCourse = new FeedbackCourse();

        feedbackCourse.setComment(dto.getComment());
        feedbackCourse.setRate(dto.getRate());
        feedbackCourse.setStatus(dto.getStatus());

        feedbackCourse.setRegistrationCourse(registrationCourse);
        feedbackCourse.setOnlineCourse(onlineCourse);

        feedbackRepository.save(feedbackCourse);
        return feedbackCourse;

    }

    public List<ResFeedbackDTO> findByMemberId(Long memberId) {
        List<FeedbackCourse> feedbackCourses = feedbackRepository.findByRegistrationCourseMemberId(memberId);
        List<ResFeedbackDTO> list = feedbackCourses.stream().map(feedback -> new ResFeedbackDTO(
                feedback.getComment(),
                feedback.getRate(),
                feedback.getStatus(),
                feedback.getRegistrationCourse().getMember().getUsername())).toList();
        return list;
    }


}
