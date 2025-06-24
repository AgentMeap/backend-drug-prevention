package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.AgeGroup;
import com.group7.swp391.drug_prevention.domain.Course;

import com.group7.swp391.drug_prevention.domain.request.ReqCourseDTO;

import com.group7.swp391.drug_prevention.domain.response.ResCourseDTO;
import com.group7.swp391.drug_prevention.repository.AgeGroupRepository;
import com.group7.swp391.drug_prevention.repository.CourseRepository;
import com.group7.swp391.drug_prevention.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    private CourseRepository courseRepository;
    private AgeGroupRepository ageGroupRepository;

    public CourseService(CourseRepository courseRepository, AgeGroupRepository ageGroupRepository) {
        this.courseRepository = courseRepository;
        this.ageGroupRepository = ageGroupRepository;
    }

    public Course createCourse(ReqCourseDTO dto) {
        Course course = new Course();
        AgeGroup ageGroup = ageGroupRepository.findById(dto.getAgeGroupId()).orElse(null);
        course.setAgeGroup(ageGroup);

        course.setName(dto.getName());
        course.setDescription(dto.getDescription());
        course.setStatus(dto.getStatus());
        course.setDuration(dto.getDuration());
        course.setImage(dto.getImage());
        course.setVideoUrl(dto.getVideoUrl());


        return courseRepository.save(course);

    }


    public Course updateSchedule(long id, ReqCourseDTO dto) {
        Course course = courseRepository.findById(id).orElse(null);

        AgeGroup ageGroup = ageGroupRepository.findById(dto.getAgeGroupId()).orElse(null);
        course.setAgeGroup(ageGroup);

        course.setName(dto.getName());
        course.setDescription(dto.getDescription());
        course.setStatus(dto.getStatus());
        course.setDuration(dto.getDuration());
        course.setImage(dto.getImage());
        course.setVideoUrl(dto.getVideoUrl());


        return courseRepository.save(course);
    }

    public List<ResCourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<ResCourseDTO> list = courses.stream().map(course -> new ResCourseDTO(
                course.getName(),
                course.getDescription(),
                course.getImage(),
                course.getVideoUrl(),
                course.getDuration(),
                course.getAgeGroup().getId())).toList();
        return list;
    }
}
