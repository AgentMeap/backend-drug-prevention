package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.AgeGroup;
import com.group7.swp391.drug_prevention.domain.OnlineCourse;

import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.domain.request.ReqCourseDTO;

import com.group7.swp391.drug_prevention.domain.response.ResCourseDTO;
import com.group7.swp391.drug_prevention.repository.AgeGroupRepository;
import com.group7.swp391.drug_prevention.repository.CourseRepository;
import com.group7.swp391.drug_prevention.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final AgeGroupRepository ageGroupRepository;
    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, AgeGroupRepository ageGroupRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.ageGroupRepository = ageGroupRepository;
        this.userRepository = userRepository;
    }

    public OnlineCourse createCourse(ReqCourseDTO dto) {
        AgeGroup ageGroup = ageGroupRepository.getById(dto.getAgeGroupId());
        OnlineCourse onlineCourse = new OnlineCourse();


        onlineCourse.setAgeGroup(ageGroup);
        onlineCourse.setName(dto.getName());
        onlineCourse.setDescription(dto.getDescription());
        onlineCourse.setStatus(dto.getStatus());
        onlineCourse.setDuration(dto.getDuration());
        onlineCourse.setImage(dto.getImage());
        onlineCourse.setVideoUrl(dto.getVideoUrl());


        onlineCourse.setCreatedAt(Instant.now());
        onlineCourse.setUpdatedAt(Instant.now());


        return courseRepository.save(onlineCourse);

    }


    public OnlineCourse updateSchedule(long id, ReqCourseDTO dto) {
        OnlineCourse onlineCourse = courseRepository.findById(id).orElse(null);

        AgeGroup ageGroup = ageGroupRepository.getById(dto.getAgeGroupId());
        onlineCourse.setAgeGroup(ageGroup);

        onlineCourse.setName(dto.getName());
        onlineCourse.setDescription(dto.getDescription());
        onlineCourse.setStatus(dto.getStatus());
        onlineCourse.setDuration(dto.getDuration());
        onlineCourse.setImage(dto.getImage());
        onlineCourse.setVideoUrl(dto.getVideoUrl());

        onlineCourse.setUpdatedAt(Instant.now());

        return courseRepository.save(onlineCourse);
    }

    public List<ResCourseDTO> getAllCoursesForMember() {
        return courseRepository.findAll().stream().map(course -> new ResCourseDTO(
                course.getName(),
                course.getDescription(),
                course.getAgeGroup().getId(),
                course.getImage(),
                course.getVideoUrl(),
                course.getDuration())).toList();
    }

    public List<OnlineCourse> getAllCourses() {
        return courseRepository.findAll();
    }

}
