package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.AgeGroup;
import com.group7.swp391.drug_prevention.domain.Course;

import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.domain.request.ReqCourseDTO;

import com.group7.swp391.drug_prevention.domain.response.ResCourseDTO;
import com.group7.swp391.drug_prevention.repository.AgeGroupRepository;
import com.group7.swp391.drug_prevention.repository.CourseRepository;
import com.group7.swp391.drug_prevention.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public Course createCourse(ReqCourseDTO dto) {
        AgeGroup ageGroup = ageGroupRepository.getById(dto.getAgeGroupId());
        Course course = new Course();


        course.setAgeGroup(ageGroup);
        course.setName(dto.getName());
        course.setDescription(dto.getDescription());
        course.setStatus(dto.getStatus());
        course.setDuration(dto.getDuration());
        course.setImage(dto.getImage());
        course.setVideoUrl(dto.getVideoUrl());


        course.setCreatedAt(Instant.now());
        course.setUpdatedAt(Instant.now());


        return courseRepository.save(course);

    }


    public Course updateSchedule(long id, ReqCourseDTO dto) {
        Course course = courseRepository.findById(id).orElse(null);

        AgeGroup ageGroup = ageGroupRepository.getById(dto.getAgeGroupId());
        course.setAgeGroup(ageGroup);

        course.setName(dto.getName());
        course.setDescription(dto.getDescription());
        course.setStatus(dto.getStatus());
        course.setDuration(dto.getDuration());
        course.setImage(dto.getImage());
        course.setVideoUrl(dto.getVideoUrl());

        course.setUpdatedAt(Instant.now());

        return courseRepository.save(course);
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

    public void registerCourse(Long memberId,long id) {
        User member = userRepository.findById(memberId).orElse(null);
        Course course = courseRepository.findById(id).orElse(null);

        List<User> users = course.getMember();
        users.add(member);
        course.setMember(users);
        courseRepository.save(course);

        List<Course>  courses = member.getListCourse();
        courses.add(course);
        member.setListCourse(courses);
        userRepository.save(member);

    }

    public List<ResCourseDTO> getListCourseByMemberId(Long memberId) {
        List<Course> courses = courseRepository.getListCourseByMemberId(memberId);

        return  courses.stream().map(course -> new ResCourseDTO(
                course.getName(),
                course.getDescription(),
                course.getAgeGroup().getId(),
                course.getImage(),
                course.getVideoUrl(),
                course.getDuration())).toList();
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

}
