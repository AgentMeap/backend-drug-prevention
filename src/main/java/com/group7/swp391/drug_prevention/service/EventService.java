package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.Event;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.domain.request.ReqEventDTO;
import com.group7.swp391.drug_prevention.domain.response.ResEventDTO;
import com.group7.swp391.drug_prevention.repository.EventRepository;
import com.group7.swp391.drug_prevention.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    private EventRepository eventRepository;
    private UserRepository userRepository;
    public EventService(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event createEvent(ReqEventDTO dto) {
        Event event = new Event();

        User manager = userRepository.getReferenceById(dto.getManagerId());
        event.setManager(manager);

        event.setDescription(dto.getDescription());
        event.setLocation(dto.getLocation());
        event.setTitle(dto.getTitle());
        event.setProgramCoordinator(dto.getProgramCoordinator());
        event.setStartTime(dto.getStartTime());
        event.setEndTime(dto.getEndTime());
        event.setCreatedAt(Instant.now());
        return eventRepository.save(event);
    }


    public Event updateEvent(Long id, ReqEventDTO dto) {
        Event event = eventRepository.findById(id).orElse(null);
        event.setDescription(dto.getDescription());
        event.setLocation(dto.getLocation());
        event.setTitle(dto.getTitle());
        event.setProgramCoordinator(dto.getProgramCoordinator());
        event.setStartTime(dto.getStartTime());
        event.setEndTime(dto.getEndTime());
        event.setUpdatedAt(Instant.now());
        return eventRepository.save(event);
    }

    public Event findEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }


    public void registerEvent(Long memberId,long id) {
        Event event = eventRepository.findById(id).orElse(null);
        User user = userRepository.findById(memberId).orElse(null);

        List<Event> listevents = user.getListEvents();
        listevents.add(event);
        user.setListEvents(listevents);
        userRepository.save(user);

        List<User> listUser = event.getMember();
        listUser.add(user);
        event.setMember(listUser);
        eventRepository.save(event);

    }

    public List<ResEventDTO> findEventByMemberId(Long memberId) {
        List<Event> events = eventRepository.getListEventByMemberId(memberId);
        List<ResEventDTO> dtos = events.stream().map(event -> new ResEventDTO(event.getTitle(),
                event.getDescription(),
                event.getLocation(),
                event.getProgramCoordinator(),
                event.getStartTime(),
                event.getEndTime()
                )).toList();
        return dtos;
    }
}
