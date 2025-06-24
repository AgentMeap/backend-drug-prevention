package com.group7.swp391.drug_prevention.controller;

import com.group7.swp391.drug_prevention.domain.Test;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.domain.request.ReqTestDTO;
import com.group7.swp391.drug_prevention.domain.response.ResTestDTO;
import com.group7.swp391.drug_prevention.service.TestService;
import com.group7.swp391.drug_prevention.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tests/")
public class TestController {
    private TestService testService;
    private UserService userService;

    public TestController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/")
    public List<ResTestDTO> getAllTests() {

        return testService.getAllTests();
    }

    @GetMapping("/getAllTestsByMemberId/{id}")
    public List<Test> getAllTestsByMemberId(@PathVariable Long id) {
        return testService.getAllTestsByMemberId(id);
    }

    @PostMapping("/createTest")
    public ResponseEntity<Test> createTest(@RequestBody ReqTestDTO dto) {
        User member = userService.fetchUserById(dto.getMemberId());
        if(member==null){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(testService.createTest(dto), HttpStatus.CREATED);
    }
}
