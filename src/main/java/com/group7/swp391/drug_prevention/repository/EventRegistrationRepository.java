package com.group7.swp391.drug_prevention.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group7.swp391.drug_prevention.domain.EventRegistration;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.util.constant.EventRegistrationStatus;

import java.util.List;

@Repository
public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Integer> {
    List<EventRegistration> findByEventId(Integer eventId);
    List<EventRegistration> findByMemberId(Integer memberId);
    boolean existsByMemberIdAndEventId(Integer memberId, Integer eventId);
    List<EventRegistration> findByMember(User member);
    List<EventRegistration> findByMemberAndEventId(User member, Integer eventId);
    List<EventRegistration> findByStatus(EventRegistrationStatus status);
}