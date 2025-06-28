package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.Booking;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.domain.request.ReqBookingDTO;
import com.group7.swp391.drug_prevention.domain.response.ResBookingDTO;
import com.group7.swp391.drug_prevention.repository.BookingRepository;
import com.group7.swp391.drug_prevention.repository.UserRepository;
import com.group7.swp391.drug_prevention.util.constant.RoleEnum;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
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
        User consultant = userRepository.findById(dto.getConsultantId()).orElse(null);
        if(member != null) {
            booking.setMember(member);
            booking.setBookingTime(dto.getBookingTime());
            booking.setCreatedAt(Instant.now());
            booking.setStatus("Pending");
            booking.setUpdatedAt(Instant.now());
            booking.setConsultant(consultant);


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

    public ResBookingDTO cancelBookingById(long id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        booking.setStatus("Cancelled");
        bookingRepository.save(booking);
        if(booking != null) {
            ResBookingDTO resBookingDTO = new ResBookingDTO();
            resBookingDTO.setStatus("Cancelled");
            resBookingDTO.setBookingTime(booking.getBookingTime());
            return resBookingDTO;
        }
        return null;
    }

    public ResBookingDTO confirmBookingById(long id) {

        Booking booking = bookingRepository.findById(id).orElse(null);
        booking.setStatus("Confirmed");
        bookingRepository.save(booking);
        if(booking != null) {
            ResBookingDTO resBookingDTO = new ResBookingDTO();
            resBookingDTO.setStatus("Confirmed");
            resBookingDTO.setBookingTime(booking.getBookingTime());
            return resBookingDTO;
        }
        return null;
    }

    public List<ResBookingDTO> findAllBookingsByMemberId(long memberId) {
        User member = userRepository.findById(memberId).orElse(null);
        if (member.getRole() != RoleEnum.MEMBER) {
            return null;
        }

        return member.getListBooking().stream()
                .map(booking -> new ResBookingDTO(booking.getBookingTime(), booking.getStatus()))
                .toList();
    }

    public List<ResBookingDTO> findAllBookingByConsultantId(long consultantId) {
        User consultant = userRepository.findById(consultantId).orElse(null);
        if (consultant.getRole() != RoleEnum.CONSULTANT) {
            return null;
        }

        return consultant.getListBooking().stream()
                .map(booking -> new ResBookingDTO(booking.getBookingTime(), booking.getStatus()))
                .toList();
    }

}
