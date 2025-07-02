package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.Event;
import com.group7.swp391.drug_prevention.domain.FeedbackEvent;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.domain.request.ReqFeedbackEventDTO;
import com.group7.swp391.drug_prevention.domain.response.ResFeedbackEventDTO;
import com.group7.swp391.drug_prevention.repository.EventRepository;
import com.group7.swp391.drug_prevention.repository.FeedbackEventRepository;
import com.group7.swp391.drug_prevention.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackEventService {
    private final FeedbackEventRepository feedbackEventRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    public FeedbackEventService(FeedbackEventRepository feedbackEventRepository, UserRepository userRepository, EventRepository eventRepository) {
        this.feedbackEventRepository = feedbackEventRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public List<FeedbackEvent> findAll(){
        return feedbackEventRepository.findAll();
    }

    public FeedbackEvent create(ReqFeedbackEventDTO dto){

        FeedbackEvent feedbackEvent = new FeedbackEvent();
        User member = userRepository.getOne(dto.getMemberId());
        Event event = eventRepository.getOne(dto.getEventId());

        feedbackEvent.setComment(dto.getComment());
        feedbackEvent.setRating(0);
        feedbackEvent.setEvent(event);
        feedbackEvent.setMember(member);
        feedbackEventRepository.save(feedbackEvent);
        return feedbackEvent;
    }

    public List<ResFeedbackEventDTO> findByMemberId(long memberId){

        List<FeedbackEvent> feedbackEvents = feedbackEventRepository.findByMemberId(memberId);
        List<ResFeedbackEventDTO> resFeedbackEventDTO = feedbackEvents.stream().map(feedbackEvent -> new ResFeedbackEventDTO(
                feedbackEvent.getRating(),
                feedbackEvent.getComment(),
                feedbackEvent.getMember(),
                feedbackEvent.getEvent()
        )).toList();


        return resFeedbackEventDTO;
    }

    public FeedbackEvent updateFeedback(Integer id, FeedbackEvent feedbackDetails) {
        FeedbackEvent feedbackevent = getFeedbackById(id);
        feedbackevent.setMember(feedbackDetails.getMember());
        feedbackevent.setEventId(feedbackDetails.getEventId());
        feedbackevent.setRating(feedbackDetails.getRating());
        feedbackevent.setComment(feedbackDetails.getComment());
        return feedbackEventRepository.save(feedbackevent);
    public List<ResFeedbackEventDTO> findByEventId(long eventId){

        List<FeedbackEvent> feedbackEvents = feedbackEventRepository.findByEventId(eventId);
        List<ResFeedbackEventDTO> resFeedbackEventDTO = feedbackEvents.stream().map(feedbackEvent -> new ResFeedbackEventDTO(
                feedbackEvent.getRating(),
                feedbackEvent.getComment(),
                feedbackEvent.getMember(),
                feedbackEvent.getEvent()
        )).toList();

        return resFeedbackEventDTO;
    }

    public void deleteFeedback(Integer id) {
        feedbackEventRepository.deleteById(id);
    }
    public ResFeedbackEventDTO ratingFeedbackEvent(int rating,long id){

        ResFeedbackEventDTO resFeedbackEventDTO = new ResFeedbackEventDTO();

        FeedbackEvent feedbackEvent = feedbackEventRepository.getOne(id);

        resFeedbackEventDTO.setComment(feedbackEvent.getComment());
        resFeedbackEventDTO.setRating(rating);
        resFeedbackEventDTO.setMember(feedbackEvent.getMember());
        resFeedbackEventDTO.setEvent(feedbackEvent.getEvent());

        feedbackEvent.setRating(rating);
        feedbackEventRepository.save(feedbackEvent);

        return resFeedbackEventDTO;
    }
}
