package com.group7.swp391.drug_prevention.repository;

import com.group7.swp391.drug_prevention.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByMemberUsername(String username);
}
