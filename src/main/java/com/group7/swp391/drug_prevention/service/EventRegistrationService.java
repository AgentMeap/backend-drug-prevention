package com.group7.swp391.drug_prevention.service;



import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.group7.swp391.drug_prevention.domain.Event;
import com.group7.swp391.drug_prevention.domain.response.ResRegistrationEventDTO;
import com.group7.swp391.drug_prevention.domain.response.file.ResEventRegistrationDTO;
import com.group7.swp391.drug_prevention.repository.EventRepository;
import com.group7.swp391.drug_prevention.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public EventRegistrationService(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }



    public EventRegistration registerForEvent(EventRegistration registration) {
        // Check if user is already registered
        if (registrationRepository.findByMemberAndEventId(
                registration.getMember(),
                registration.getEventId()).size() > 0) {
            throw new IllegalArgumentException("Bạn đã đăng ký sự kiện này rồi");
        }
        registration.setStatus(EventRegistrationStatus.PENDING);
        Event event = eventRepository.getReferenceById(registration.getEventId());
        User user = userRepository.getReferenceById(registration.getMember().getId());
        event.getMember().add(user);
        eventRepository.save(event);
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

    public ResEventRegistrationDTO checkOut(long memberId,int eventId,EventRegistrationStatus status) {
        EventRegistration eventRegistration = new EventRegistration();
        User member = userRepository.getReferenceById(memberId);
        Event event = eventRepository.getReferenceById(eventId);


        if (eventRegistration.getEvents() == null) {
            eventRegistration.setEvents(new ArrayList<>());
        }

        eventRegistration.setEventId(eventId);
        eventRegistration.setMember(member);
        eventRegistration.setStatus(status);
        eventRegistration.setRegistrationDate(Instant.now());
        registrationRepository.save(eventRegistration);


        ResEventRegistrationDTO dto = new ResEventRegistrationDTO();
        dto.setEmail(member.getEmail());
        dto.setPhoneNumber(member.getPhoneNumber());
        dto.setMemberName(member.getLastName() + " " + member.getFirstName());

        dto.setTitle(event.getTitle());
        dto.setLocation(event.getLocation());
        dto.setStartDate(event.getStartDate());
        dto.setEndDate(event.getEndDate());

        dto.setStatus(status);
        dto.setRegistrationDate(eventRegistration.getRegistrationDate());

        return dto;
    }
}