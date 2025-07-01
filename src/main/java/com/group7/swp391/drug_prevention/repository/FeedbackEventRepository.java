package com.group7.swp391.drug_prevention.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group7.swp391.drug_prevention.domain.FeedbackEvent;
import com.group7.swp391.drug_prevention.domain.User;

import java.util.List;

@Repository
public interface FeedbackEventRepository extends JpaRepository<FeedbackEvent, Integer> {
    List<FeedbackEvent> findByEventId(int eventId);
    List<FeedbackEvent> findByMember(User member);
}