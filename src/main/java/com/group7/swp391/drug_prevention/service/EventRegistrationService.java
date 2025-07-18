package com.group7.swp391.drug_prevention.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group7.swp391.drug_prevention.domain.EventRegistration;
import com.group7.swp391.drug_prevention.util.constant.EventRegistrationStatus;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.repository.EventRegistrationRepository;

@Service
public class EventRegistrationService {
    @Autowired
    private EventRegistrationRepository registrationRepository;

    public EventRegistration registerForEvent(EventRegistration registration) {
        // Check if user is already registered
        if (registrationRepository.findByMemberAndEventId(
                registration.getMember(),
                registration.getEventId()).size() > 0) {
            throw new IllegalArgumentException("Bạn đã đăng ký sự kiện này rồi");
        }
        registration.setStatus(EventRegistrationStatus.PENDING);
        return registrationRepository.save(registration);
    }

    public EventRegistration approveRegistration(Integer registrationId) {
        EventRegistration reg = registrationRepository.findById(registrationId).orElseThrow();
        reg.setStatus(EventRegistrationStatus.APPROVED);
        return registrationRepository.save(reg);
    }

    public EventRegistration rejectRegistration(Integer registrationId) {
        EventRegistration reg = registrationRepository.findById(registrationId).orElseThrow();
        reg.setStatus(EventRegistrationStatus.REJECTED);
        return registrationRepository.save(reg);
    }
    public List<EventRegistration> getAllRegistrations() {
        return registrationRepository.findAll();
    }
    public List<EventRegistration> getRegistrationsByEvent(Integer eventId) {
        return registrationRepository.findByEventId(eventId);
    }

    public List<EventRegistration> getRegistrationsByMember(Integer memberId) {
        return registrationRepository.findByMemberId(memberId);
    }

    public List<EventRegistration> getRegistrationsByMember(User member) {
        return registrationRepository.findByMember(member);
    }

    public List<EventRegistration> getRegistrationsByStatus(EventRegistrationStatus status) {
        return registrationRepository.findByStatus(status);
    }
}