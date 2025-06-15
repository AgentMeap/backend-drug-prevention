package com.group7.swp391.drug_prevention.controller;

import com.group7.swp391.drug_prevention.domain.Booking;
import com.group7.swp391.drug_prevention.domain.request.ReqBookingDTO;
import com.group7.swp391.drug_prevention.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {
    private final BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/createBooking")
    public ResponseEntity<?> createBooking(@RequestBody ReqBookingDTO dto) {
        Booking booking = bookingService.createBooking(dto);
        if(booking != null) {
            return new ResponseEntity<>(booking, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("the id is not member!!",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAllBookings")
    public ResponseEntity<?> findAllBookings() {
        List<Booking> bookingsList = bookingService.findAllBookings();
        if(bookingsList == null) {
            return new ResponseEntity<>("No booking at list!!",HttpStatus.OK);
        }
        return new ResponseEntity<>(bookingsList, HttpStatus.OK);
    }

    @DeleteMapping("/deletes/{id}")
    public ResponseEntity<?> deleteBooking(@RequestParam Long id) {
        boolean check = false;
        if(bookingService.deleteBookingById(id)) {
            return new ResponseEntity<>("Booking deleted!!", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Id is not exist!!", HttpStatus.BAD_REQUEST);
        }
    }
}
