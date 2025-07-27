package com.group7.swp391.drug_prevention.controller;


import com.group7.swp391.drug_prevention.domain.response.file.ResEventRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.group7.swp391.drug_prevention.domain.EventRegistration;
import com.group7.swp391.drug_prevention.service.EventRegistrationService;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.domain.response.ResRegistrationEventDTO;
import com.group7.swp391.drug_prevention.domain.request.ReqEventRegistrationDTO;
import com.group7.swp391.drug_prevention.repository.UserRepository;
import com.group7.swp391.drug_prevention.util.constant.EventRegistrationStatus;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
public class EventRegistrationController {
    @Autowired
    private EventRegistrationService registrationService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRegistrationService eventRegistrationService;

    @GetMapping
    public List<ResRegistrationEventDTO> getAllRegistrations() {
        return registrationService.getAllRegistrations().stream().map(reg -> {
            ResRegistrationEventDTO dto = new ResRegistrationEventDTO();
            dto.setId(reg.getId() != null ? reg.getId().longValue() : null);
            dto.setEventId(reg.getEventId() != null ? reg.getEventId().longValue() : null);
            dto.setMemberId(reg.getMember() != null ? reg.getMember().getId() : null);
            dto.setStatus(reg.getStatus() != null ? reg.getStatus().name() : null);
            // Nếu có trường registeredAt thì set, còn không thì bỏ qua
            return dto;
        }).collect(java.util.stream.Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> registerForEvent(@RequestBody ReqEventRegistrationDTO dto) {
        try {
            System.out.println("=== TEST LOG: Controller loaded ===");
            System.out.println("[DEBUG] DTO received: memberId=" + dto.getMemberId() + ", eventId=" + dto.getEventId());
            User member = userRepository.findById(dto.getMemberId()).orElseThrow();
            EventRegistration registration = new EventRegistration();
            registration.setMember(member);
            registration.setEventId(dto.getEventId() != null ? dto.getEventId().intValue() : null);
            System.out.println("[DEBUG] Entity to save: member.id=" + (registration.getMember() != null ? registration.getMember().getId() : null) + ", eventId=" + registration.getEventId());
            EventRegistration result = registrationService.registerForEvent(registration);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(500).body("Internal Server Error: " + ex.getMessage());
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

    @GetMapping("/search-by-status")
    public List<ResRegistrationEventDTO> getRegistrationsByStatus(@RequestParam String status) {
        EventRegistrationStatus enumStatus;
        try {
            enumStatus = EventRegistrationStatus.valueOf(status.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Trạng thái không hợp lệ. Chọn một trong: PENDING, APPROVED, REJECTED");
        }
        return registrationService.getRegistrationsByStatus(enumStatus).stream().map(reg -> {
            ResRegistrationEventDTO dto = new ResRegistrationEventDTO();
            dto.setId(reg.getId() != null ? reg.getId().longValue() : null);
            dto.setEventId(reg.getEventId() != null ? reg.getEventId().longValue() : null);
            dto.setMemberId(reg.getMember() != null ? reg.getMember().getId() : null);
            dto.setStatus(reg.getStatus() != null ? reg.getStatus().name() : null);
            return dto;
        }).collect(java.util.stream.Collectors.toList());
    }

    @PutMapping("/approve/{id}")
    public ResRegistrationEventDTO approveRegistration(@PathVariable Long id) {
        EventRegistration reg = registrationService.approveRegistration(id.intValue());
        ResRegistrationEventDTO dto = new ResRegistrationEventDTO();
        dto.setId(reg.getId() != null ? reg.getId().longValue() : null);
        dto.setEventId(reg.getEventId() != null ? reg.getEventId().longValue() : null);
        dto.setMemberId(reg.getMember() != null ? reg.getMember().getId() : null);
        dto.setStatus(reg.getStatus() != null ? reg.getStatus().name() : null);
        return dto;
    }

    @PutMapping("/reject/{id}")
    public ResRegistrationEventDTO rejectRegistration(@PathVariable Long id) {
        EventRegistration reg = registrationService.rejectRegistration(id.intValue());
        ResRegistrationEventDTO dto = new ResRegistrationEventDTO();
        dto.setId(reg.getId() != null ? reg.getId().longValue() : null);
        dto.setEventId(reg.getEventId() != null ? reg.getEventId().longValue() : null);
        dto.setMemberId(reg.getMember() != null ? reg.getMember().getId() : null);
        dto.setStatus(reg.getStatus() != null ? reg.getStatus().name() : null);
        return dto;
    }

    @PostMapping("/checkOut/{memberId}/{eventId}/{status}")
    @PreAuthorize("hasAnyRole('STAFF')")
    public ResponseEntity<?> checkOut(@PathVariable Long memberId, @PathVariable Integer eventId, @RequestParam EventRegistrationStatus status) {
        ResEventRegistrationDTO dto = eventRegistrationService.checkOut(memberId,eventId,status);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}