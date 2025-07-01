package com.group7.swp391.drug_prevention.service;



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

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Integer id, Event eventDetails) {
        Event event = getEventById(id);
        event.setManager(eventDetails.getManager());
        event.setTitle(eventDetails.getTitle());
        event.setProgramCoordinator(eventDetails.getProgramCoordinator());
        event.setDescription(eventDetails.getDescription());
        event.setLocation(eventDetails.getLocation());
        event.setImageUrl(eventDetails.getImageUrl());
        event.setStartDate(eventDetails.getStartDate());
        event.setEndDate(eventDetails.getEndDate());
        event.setCreatedAt(eventDetails.getCreatedAt());
        event.setUpdatedAt(eventDetails.getUpdatedAt());
        return eventRepository.save(event);
    }

    public void deleteEvent(Integer id) {
        eventRepository.deleteById(id);
    }

    public List<Event> getEventsByManager(User manager) {
        return eventRepository.findByManager(manager);
    }
}