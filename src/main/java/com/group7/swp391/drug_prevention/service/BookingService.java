package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.Booking;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.domain.request.ReqBookingDTO;
import com.group7.swp391.drug_prevention.repository.BookingRepository;
import com.group7.swp391.drug_prevention.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class BookingService {
    private BookingRepository bookingRepository;
    private UserRepository userRepository;

    public BookingService(BookingRepository bookingRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    public Booking createBooking(ReqBookingDTO dto) {
        Booking booking = new Booking();
        User member = userRepository.findById(dto.getMemberId()).orElse(null);
        if(member != null && member.getRole().equals("member")) {
            booking.setMember(member);
            booking.setBookingTime(dto.getBookingTime());
            booking.setCreatedAt(LocalTime.now());

            booking.setUpdatedAt(LocalTime.now());
            return bookingRepository.save(booking);

        }
        return null;
    }

    public List<Booking> findAllBookings() {
        return bookingRepository.findAll();
    }

    public boolean deleteBookingById(long id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if(booking != null) {
            bookingRepository.delete(booking);
            return true;
        }else{
            return false;
        }
    }

    public List<Booking> getBookingsByMemberId(long id) {
        if(bookingRepository.findById(id) != null) {
            return bookingRepository.findByMemberId(id);
        }else{
            return null;
        }

    }
}
