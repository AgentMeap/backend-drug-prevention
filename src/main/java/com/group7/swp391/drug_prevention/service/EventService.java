package com.group7.swp391.drug_prevention.service;



import com.group7.swp391.drug_prevention.domain.request.ReqEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group7.swp391.drug_prevention.domain.Event;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.repository.EventRepository;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Integer id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
    }

    public Event createEvent(ReqEventDTO dto) {
        Event event = new Event();

        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setStartDate(dto.getStartDate());
        event.setEndDate(dto.getEndDate());
        event.setProgramCoordinator(dto.getProgramCoordinator());
        event.setImageUrl(dto.getImageUrl());
        event.setLocation(dto.getLocation());

        return eventRepository.save(event);
    }

    public Event updateEvent(long id, ReqEventDTO dto) {

        Event event = eventRepository.findById(id);

        event.setDescription(dto.getDescription());
        event.setLocation(dto.getLocation());
        event.setEndDate(dto.getEndDate());
        event.setStartDate(dto.getStartDate());
        event.setImageUrl(event.getImageUrl());
        event.setProgramCoordinator(event.getProgramCoordinator());
        event.setTitle(event.getTitle());


        return eventRepository.save(event);
    }

    public void deleteEvent(Integer id) {
        eventRepository.deleteById(id);
    }

    public List<Event> getEventsByManager(User manager) {
        return eventRepository.findByManager(manager);
    }
}