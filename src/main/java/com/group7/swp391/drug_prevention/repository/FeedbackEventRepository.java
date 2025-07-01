package com.group7.swp391.drug_prevention.repository;

import com.group7.swp391.drug_prevention.domain.FeedbackEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group7.swp391.drug_prevention.domain.FeedbackEvent;
import com.group7.swp391.drug_prevention.domain.User;

import java.util.List;

@Repository
public interface FeedbackEventRepository extends JpaRepository<FeedbackEvent,Long> {
    List<FeedbackEvent> findByMemberId(long memberId);
    List<FeedbackEvent> findByEventId(long eventId);
}
