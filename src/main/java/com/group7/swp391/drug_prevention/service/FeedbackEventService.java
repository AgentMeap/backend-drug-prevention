package com.group7.swp391.drug_prevention.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group7.swp391.drug_prevention.domain.FeedbackEvent;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.repository.FeedbackEventRepository;

import java.util.List;

@Service
public class FeedbackEventService {
    private final FeedbackEventRepository feedbackEventRepository;

    @Autowired
    public FeedbackEventService(FeedbackEventRepository feedbackRepository) {
        this.feedbackEventRepository = feedbackRepository;
    }

    public List<FeedbackEvent> getAllFeedbacks() {
        return feedbackEventRepository.findAll();
    }

    public FeedbackEvent getFeedbackById(Integer id) {
        return feedbackEventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found with id: " + id));
    }

    public FeedbackEvent createFeedback(FeedbackEvent feedback) {
        return feedbackEventRepository.save(feedback);
    }

    public FeedbackEvent updateFeedback(Integer id, FeedbackEvent feedbackDetails) {
        FeedbackEvent feedbackevent = getFeedbackById(id);
        feedbackevent.setMember(feedbackDetails.getMember());
        feedbackevent.setEventId(feedbackDetails.getEventId());
        feedbackevent.setRating(feedbackDetails.getRating());
        feedbackevent.setComment(feedbackDetails.getComment());
        return feedbackEventRepository.save(feedbackevent);
    }

    public void deleteFeedback(Integer id) {
        feedbackEventRepository.deleteById(id);
    }

    public List<FeedbackEvent> getFeedbacksByEventId(int eventId) {
        return feedbackEventRepository.findByEventId(eventId);
    }

    public List<FeedbackEvent> getFeedbacksByMember(User member) {
        return feedbackEventRepository.findByMember(member);
    }
}