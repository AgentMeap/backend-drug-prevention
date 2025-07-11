package com.group7.swp391.drug_prevention.controller;

import com.group7.swp391.drug_prevention.domain.request.ReqCourseDTO;
import com.group7.swp391.drug_prevention.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @PostMapping("/createCourse/")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ResponseEntity<?> createCourse(@RequestBody ReqCourseDTO dto) {
        courseService.createCourse(dto);
        return new ResponseEntity<>("Created!!!",HttpStatus.CREATED);
    }

    @PutMapping("/updateCourse/{id}")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ResponseEntity<?> updateCourse(@RequestParam long id,@RequestBody ReqCourseDTO dto) {
        courseService.updateCourse(id, dto);
        return new ResponseEntity<>("Updated!!!",HttpStatus.OK);
    }

    @GetMapping("/getAllCourse")
    public ResponseEntity<?> getAllCourse() {
        return new ResponseEntity<>(courseService.getAllCourses(),HttpStatus.OK);
    }

    @GetMapping("/getAllCourseForMember")
    public ResponseEntity<?> getAllCourseForMember() {
        return new ResponseEntity<>(courseService.getAllCoursesForMember(),HttpStatus.OK);
    }

    @DeleteMapping("/deleteCourse")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ResponseEntity<?> deleteCourse(long id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
