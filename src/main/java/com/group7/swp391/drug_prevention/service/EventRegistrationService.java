package com.group7.swp391.drug_prevention.service;



import java.util.List;

import com.group7.swp391.drug_prevention.domain.Event;
import com.group7.swp391.drug_prevention.domain.request.ReqEventRegistrationDTO;
import com.group7.swp391.drug_prevention.repository.EventRepository;
import com.group7.swp391.drug_prevention.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group7.swp391.drug_prevention.domain.EventRegistration;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.repository.EventRegistrationRepository;

@Service
public class EventRegistrationService {
    @Autowired
    private EventRegistrationRepository registrationRepository;

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    public EventRegistrationService(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }


    public EventRegistration registerForEvent(ReqEventRegistrationDTO dto) {
        // Check if user is already registered
        EventRegistration registration = new EventRegistration();
        User member = userRepository.getOne(dto.getMemberId());
        Event event = eventRepository.findById(dto.getEventId());
        registration.setEvent(event);
        registration.setMember(member);
        return registrationRepository.save(registration);
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
}