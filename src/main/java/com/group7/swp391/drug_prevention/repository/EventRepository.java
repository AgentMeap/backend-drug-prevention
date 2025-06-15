package com.group7.swp391.drug_prevention.repository;

import com.group7.swp391.drug_prevention.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
