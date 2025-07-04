package com.group7.swp391.drug_prevention.controller;




import com.group7.swp391.drug_prevention.domain.request.ReqEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.group7.swp391.drug_prevention.domain.Event;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.service.EventService;
import com.group7.swp391.drug_prevention.domain.response.ResEventDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResEventDTO> getEventById(@PathVariable Integer id) {
        try {
            Event event = eventService.getEventById(id);
            if (event == null) {
                return ResponseEntity.notFound().build();
            }
            ResEventDTO dto = new ResEventDTO();
            dto.setTitle(event.getTitle());
            dto.setDescription(event.getDescription());
            dto.setLocation(event.getLocation());
            dto.setImageUrl(event.getImageUrl());
            dto.setProgramCoordinator(event.getProgramCoordinator());
            dto.setStartDate(event.getStartDate() != null ? event.getStartDate() : null);
            dto.setEndDate(event.getEndDate() != null ? event.getEndDate() : null);

            if (event.getManager() != null) {
                dto.setManagerId(event.getManager().getId());
                dto.setManagerName(event.getManager().getFirstName() + " " + event.getManager().getLastName());
            }
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody ReqEventDTO dto) {
        try {
            Event createdEvent = eventService.createEvent(dto);
            return ResponseEntity.ok(createdEvent);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Integer id, @RequestBody ReqEventDTO dto) {
        try {
            Event updatedEvent = eventService.updateEvent(id, dto);
            return ResponseEntity.ok(updatedEvent);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Integer id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/manager/{managerId}")
    public List<Event> getEventsByManager(@PathVariable Long managerId) {
        User manager = new User();
        manager.setId(managerId);
        return eventService.getEventsByManager(manager);
    }
}