package com.group7.swp391.drug_prevention.controller;

import com.group7.swp391.drug_prevention.domain.Event;
import com.group7.swp391.drug_prevention.domain.request.ReqEventDTO;
import com.group7.swp391.drug_prevention.domain.response.ResEventDTO;
import com.group7.swp391.drug_prevention.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventService eventService;
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/getAllEvent")
    public List<Event> getAllEvent() {
        return eventService.getAllEvents();
    }

    @PostMapping("/createEvent")
    public Event createEvent(@RequestBody ReqEventDTO dto) {
        return eventService.createEvent(dto);
    }

    @PutMapping("/updateEvent")
    public Event updateEvent(@RequestParam long id,@RequestBody ReqEventDTO dto) {
        return eventService.updateEvent(id, dto);
    }

    @GetMapping("/findEventById/{id}")
    public Event findEventById(@PathVariable long id) {
        return eventService.findEventById(id);
    }

    @PutMapping("/registerEvent/{memberId}/{id}")
    public ResponseEntity<?> registerEvent(@PathVariable long memberId,@PathVariable long id) {
        eventService.registerEvent(id, memberId);
        return new ResponseEntity<>("Registered Successfully",HttpStatus.OK);
    }

    @GetMapping("/findListEventByMemberId{memberId}")
    public List<Event> findListEventByMemberId(@PathVariable long memberId) {
        return eventService.findEventByMemberId(memberId);
    }
}
