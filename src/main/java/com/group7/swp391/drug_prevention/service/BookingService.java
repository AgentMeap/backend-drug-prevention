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
        if (member != null) {
            booking.setMember(member);
            booking.setBookingTime(dto.getBookingTime());
            booking.setCreatedAt(Instant.now());
            booking.setStatus("Chờ xác nhận");
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
        if (booking != null) {
            bookingRepository.delete(booking);
            return true;
        } else {
            return false;
        }
    }

    public ResBookingDTO cancelBookingById(long id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        booking.setStatus("Đã huỷ");
        bookingRepository.save(booking);
        if (booking != null) {
            ResBookingDTO resBookingDTO = new ResBookingDTO();
            resBookingDTO.setStatus("Đã huỷ");
            resBookingDTO.setBookingTime(booking.getBookingTime());
            resBookingDTO.setConsultant(booking.getConsultant());
            resBookingDTO.setMember(booking.getMember());
            return resBookingDTO;
        }
        return null;
    }

    public ResBookingDTO confirmBookingById(long id) {

        Booking booking = bookingRepository.findById(id).orElse(null);
        booking.setStatus("Đã xác nhận");
        bookingRepository.save(booking);
        if (booking != null) {
            ResBookingDTO resBookingDTO = new ResBookingDTO();
            resBookingDTO.setStatus("Đã xác nhận");
            resBookingDTO.setBookingTime(booking.getBookingTime());
            resBookingDTO.setConsultant(booking.getConsultant());
            resBookingDTO.setMember(booking.getMember());
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
                .map(booking -> new ResBookingDTO(
                        booking.getId(),
                        booking.getBookingTime(),
                        booking.getStatus(),
                        booking.getConsultant()
                ))
                .toList();
    }

    public List<ResBookingDTO> findAllBookingByConsultantId(long consultantId) {
        User consultant = userRepository.findById(consultantId).orElse(null);
        if (consultant.getRole() != RoleEnum.CONSULTANT) {
            return null;
        }

        return consultant.getBookedList().stream().map(booking -> new ResBookingDTO(
                booking.getId(),
                booking.getMember(),
                booking.getStatus(),
                booking.getBookingTime(),
                booking.getConsultant()

        )).toList();

    }


    public ResBookingDTO afterConsultationDone(long id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        booking.setStatus("Hoàn thành");
        bookingRepository.save(booking);
        ResBookingDTO resBookingDTO = new ResBookingDTO();
        if (booking != null) {
            resBookingDTO.setStatus("Hoàn thành");
            resBookingDTO.setBookingTime(booking.getBookingTime());
            resBookingDTO.setConsultant(booking.getConsultant());
            resBookingDTO.setMember(booking.getMember());
            return resBookingDTO;
        }
        return null;
    }


}
