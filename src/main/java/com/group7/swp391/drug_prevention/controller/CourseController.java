package com.group7.swp391.drug_prevention.controller;

import com.group7.swp391.drug_prevention.domain.request.ReqCourseDTO;
import com.group7.swp391.drug_prevention.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {
    private final CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/createCourse")
    public ResponseEntity<?> createCourse(@RequestBody ReqCourseDTO dto) {
        courseService.createCourse(dto);
        return new ResponseEntity<>("Created!!!",HttpStatus.CREATED);
    }

    @PutMapping("/updateCourse/{id}")
    public ResponseEntity<?> updateCourse(@RequestParam long id,@RequestBody ReqCourseDTO dto) {
        courseService.updateSchedule(id, dto);
        return new ResponseEntity<>("Updated!!!",HttpStatus.OK);
    }

    @GetMapping("/getAllCourse")
    public ResponseEntity<?> getAllCourse() {
        return new ResponseEntity<>(courseService.getAllCourses(),HttpStatus.OK);
    }

    @PutMapping("/registerCourse/{memberId}/{id}")
    public ResponseEntity<?> registerCourse(@PathVariable long memberId,@PathVariable long id) {
        courseService.registerCourse(memberId,id);
        return new ResponseEntity<>("Registered!!!",HttpStatus.OK);
    }

    @GetMapping("/getCourseByMemberId/{memberId}")
    public ResponseEntity<?> getCourseByMemberId(@PathVariable long memberId) {
        return new ResponseEntity<>(courseService.getListCourseByMemberId(memberId),HttpStatus.OK);
    }
}
