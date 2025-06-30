package com.group7.swp391.drug_prevention.controller;

import com.group7.swp391.drug_prevention.domain.Registration;
import com.group7.swp391.drug_prevention.domain.request.ReqRegistrationDTO;
import com.group7.swp391.drug_prevention.domain.response.ResRegistrationDTO;
import com.group7.swp391.drug_prevention.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {
    private final RegistrationService registrationService;


    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/")
    public List<Registration> findAll() {
        return registrationService.getRegistrations();
    }

    @PostMapping("/create")
    public Registration createRegistration(ReqRegistrationDTO dto) {
        return registrationService.createRegistration(dto);
    }

    @GetMapping("/getByMemberId")
    public ResponseEntity<List<ResRegistrationDTO>> getByMemberId(long memberId) {
        return new ResponseEntity<>(registrationService.getRegistrationByMemberId(memberId), HttpStatus.OK);
    }
}
