package com.group7.swp391.drug_prevention.controller;

import com.group7.swp391.drug_prevention.domain.Feedback;
import com.group7.swp391.drug_prevention.domain.request.ReqFeedbackDTO;
import com.group7.swp391.drug_prevention.domain.response.ResFeedbackDTO;
import com.group7.swp391.drug_prevention.service.FeedbackService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedback")
public class FeedbackController {
    private FeedbackService feedbackService;
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("/getAllFeedback")
    public List<Feedback> getAllFeedback() {
        return feedbackService.findAll();
    }

    @GetMapping("/getFeedbackByMemberId{memberId}")
    public List<ResFeedbackDTO> getFeedbackByMemberId(@PathVariable long memberId) {
       return feedbackService.findByMemberId(memberId);
    }

    @PostMapping("/create")
    public Feedback createFeedback(@RequestBody ReqFeedbackDTO dto) {
        return feedbackService.create(dto);
    }
}
