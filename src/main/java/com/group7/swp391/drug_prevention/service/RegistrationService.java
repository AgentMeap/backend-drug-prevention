package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.Course;
import com.group7.swp391.drug_prevention.domain.Registration;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.domain.request.ReqRegistrationDTO;
import com.group7.swp391.drug_prevention.domain.response.ResRegistrationDTO;
import com.group7.swp391.drug_prevention.repository.CourseRepository;
import com.group7.swp391.drug_prevention.repository.RegistrationRepository;
import com.group7.swp391.drug_prevention.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    public RegistrationService(RegistrationRepository registrationRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.registrationRepository = registrationRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public List<Registration> getRegistrations() {
        return registrationRepository.findAll();
    }

    public Registration createRegistration(ReqRegistrationDTO dto) {
        User member = userRepository.findById(dto.getMemberId()).orElse(null);
        Course course = courseRepository.findById(dto.getCourseId()).orElse(null);


        Registration registration = new Registration();

        registration.setMember(member);
        registration.setCourse(course);
        registration.setRegistrationDate(Instant.now());
        registration.setStatus("Đang diễn ra");

        return registrationRepository.save(registration);

    }

    public List<ResRegistrationDTO> getRegistrationByMemberId(long memberId) {
        List<Registration> dtos = registrationRepository.findByMemberId(memberId);
        List<ResRegistrationDTO> dtoList = new ArrayList<>();

        return dtos.stream().map(registration -> new ResRegistrationDTO(
                registration.getStatus(),
                registration.getCourse()
        )).toList();
    }

}
