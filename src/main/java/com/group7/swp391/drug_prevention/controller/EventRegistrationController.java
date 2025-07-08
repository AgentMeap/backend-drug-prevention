package com.group7.swp391.drug_prevention.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.group7.swp391.drug_prevention.domain.EventRegistration;
import com.group7.swp391.drug_prevention.service.EventRegistrationService;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.domain.response.ResRegistrationEventDTO;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
public class EventRegistrationController {
    @Autowired
    private EventRegistrationService registrationService;
    @GetMapping
    public List<ResRegistrationEventDTO> getAllRegistrations() {
        return registrationService.getAllRegistrations().stream().map(reg -> {
            ResRegistrationEventDTO dto = new ResRegistrationEventDTO();
            dto.setId(reg.getId() != null ? reg.getId().longValue() : null);
            dto.setEventId(reg.getEventId() != null ? reg.getEventId().longValue() : null);
            dto.setMemberId(reg.getMember() != null ? reg.getMember().getId() : null);
            // Nếu có trường registeredAt thì set, còn không thì bỏ qua
            return dto;
        }).collect(java.util.stream.Collectors.toList());
    }
    @PostMapping
    public ResponseEntity<?> registerForEvent(@RequestBody EventRegistration registration) {
        try {
            EventRegistration result = registrationService.registerForEvent(registration);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/event/{eventId}")
    public List<EventRegistration> getRegistrationsForEvent(@PathVariable Integer eventId) {
        return registrationService.getRegistrationsByEvent(eventId);
    }

    @GetMapping("/member/{memberId}")
    public List<EventRegistration> getRegistrationsForMemberOld(@PathVariable Long memberId) {
        // Deprecated: chỉ để tương thích cũ, nên dùng /user/{memberId}
        User member = new User();
        member.setId(memberId);
        return registrationService.getRegistrationsByMember(member);
    }

    @GetMapping("/user/{memberId}")
    public List<EventRegistration> getRegistrationsByMember(@PathVariable Long memberId) {
        User member = new User();
        member.setId(memberId);
        return registrationService.getRegistrationsByMember(member);
    }
}