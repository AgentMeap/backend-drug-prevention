package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.Event;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.domain.request.ReqEventDTO;
import com.group7.swp391.drug_prevention.repository.EventRepository;
import com.group7.swp391.drug_prevention.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
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

        User manager = userRepository.getById(dto.getManagerId());
        event.setManager(manager);

        event.setDescription(dto.getDescription());
        event.setLocation(dto.getLocation());
        event.setTitle(dto.getTitle());
        event.setProgramCoordinator(dto.getProgramCoordinator());
        return eventRepository.save(event);
    }


    public Event updateEvent(Long id, ReqEventDTO dto) {
        Event event = eventRepository.findById(id).orElse(null);
        event.setDescription(dto.getDescription());
        event.setLocation(dto.getLocation());
        event.setTitle(dto.getTitle());
        event.setProgramCoordinator(dto.getProgramCoordinator());
        return eventRepository.save(event);
    }

    public Event findEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }
}
