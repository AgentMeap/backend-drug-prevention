package com.group7.swp391.drug_prevention.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.group7.swp391.drug_prevention.domain.FeedbackEvent;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.domain.response.ResFeedbackEventDTO;
import com.group7.swp391.drug_prevention.service.FeedbackEventService;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class FeedbackEventController {
    private final FeedbackEventService feedbackService;

    @Autowired
    public FeedbackEventController(FeedbackEventService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public List<FeedbackEvent> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/event/{eventId}")
    public List<ResFeedbackEventDTO> getFeedbacksByEventId(@PathVariable int eventId) {
        return feedbackService.getFeedbacksByEventId(eventId).stream().map(feedback -> {
            ResFeedbackEventDTO dto = new ResFeedbackEventDTO();
            dto.setId(feedback.getId());
            dto.setEventId(feedback.getEventId());
            dto.setMemberId(feedback.getMember() != null ? feedback.getMember().getId() : null);
            dto.setRating(feedback.getRating());
            dto.setComment(feedback.getComment());
            return dto;
        }).collect(java.util.stream.Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackEvent> getFeedbackById(@PathVariable Integer id) {
        try {
            FeedbackEvent feedback = feedbackService.getFeedbackById(id);
            return ResponseEntity.ok(feedback);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<FeedbackEvent> addFeedback(@RequestBody FeedbackEvent feedback) {
        try {
            FeedbackEvent createdFeedback = feedbackService.createFeedback(feedback);
            return ResponseEntity.ok(createdFeedback);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackEvent> updateFeedback(@PathVariable Integer id, @RequestBody FeedbackEvent feedbackDetails) {
        try {
            FeedbackEvent updatedFeedback = feedbackService.updateFeedback(id, feedbackDetails);
            return ResponseEntity.ok(updatedFeedback);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Integer id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/member/{memberId}")
    public List<FeedbackEvent> getFeedbacksByMember(@PathVariable Long memberId) {
        User member = new User();
        member.setId(memberId);
        return feedbackService.getFeedbacksByMember(member);
    }
}