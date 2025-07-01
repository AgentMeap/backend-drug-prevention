package com.group7.swp391.drug_prevention.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.group7.swp391.drug_prevention.domain.FeedbackEvent;
import com.group7.swp391.drug_prevention.domain.request.ReqFeedbackEventDTO;
import com.group7.swp391.drug_prevention.domain.response.ResFeedbackEventDTO;
import com.group7.swp391.drug_prevention.service.FeedbackEventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedbackEvent")
public class FeedbackEventController {
    private final FeedbackEventService feedbackEventService;
    public FeedbackEventController(FeedbackEventService feedbackEventService) {
        this.feedbackEventService = feedbackEventService;
    }

    @GetMapping("/getAll")
    public List<FeedbackEvent> getAll(){
        return feedbackEventService.findAll();
    }

    @PostMapping("/create")
    public FeedbackEvent create(@RequestBody ReqFeedbackEventDTO dto){
        return feedbackEventService.create(dto);
    }

    @GetMapping("/getByMemberId/{memberId}")
    public List<ResFeedbackEventDTO> getByMemberId(@PathVariable long memberId){
        return feedbackEventService.findByMemberId(memberId);
    }

    @GetMapping("/getByEventId/{eventId}")
    public List<ResFeedbackEventDTO> getByEventId(@PathVariable long eventId){
        return feedbackEventService.findByEventId(eventId);
    }

    @PutMapping("/rating/{rating}/{id}")
    public ResFeedbackEventDTO updateRating(@PathVariable int rating,@PathVariable long id){
        return feedbackEventService.ratingFeedbackEvent(rating,id);

    }
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Integer id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }

}