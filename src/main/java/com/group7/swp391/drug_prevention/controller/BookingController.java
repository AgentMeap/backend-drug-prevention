package com.group7.swp391.drug_prevention.controller;

import com.group7.swp391.drug_prevention.domain.Booking;
import com.group7.swp391.drug_prevention.domain.request.ReqBookingDTO;
import com.group7.swp391.drug_prevention.domain.response.ResBookingDTO;
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

    @PostMapping("/")
    public ResponseEntity<?> createBooking(@RequestBody ReqBookingDTO dto) {
        Booking booking = bookingService.createBooking(dto);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
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

    @PutMapping("/cancelBookingById/{id}")
    public ResponseEntity<?> cancelBookingById(@PathVariable long id) {
        if(bookingService.cancelBookingById(id) != null){
            return new ResponseEntity<>(bookingService.cancelBookingById(id), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Id is not exist!!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/confirmBookingById/{id}")
    public ResponseEntity<?> confirmBookingById(@PathVariable long id) {
        if(bookingService.confirmBookingById(id) != null){
            return new ResponseEntity<>(bookingService.confirmBookingById(id), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Id is not exist!!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getListBookingByMemberId/{memberId}")
    public ResponseEntity<?> getListBookingByMemberId(@PathVariable long memberId) {
        List<ResBookingDTO> dtos = bookingService.findAllBookingsByMemberId(memberId);
        if (dtos == null) {
            return new ResponseEntity<>("You must be MEMBER !!!", HttpStatus.FORBIDDEN); // 403 rõ ràng hơn
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/getListBookingByConsultantId/{consultantId}")
    public ResponseEntity<?> getListBookingByConsultantId(@PathVariable long consultantId) {
        List<ResBookingDTO> dtos = bookingService.findAllBookingByConsultantId(consultantId);
        if (dtos == null) {
            return new ResponseEntity<>("You must be CONSULTANT !!!", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/afterConsultation/{id}")
    public ResponseEntity<ResBookingDTO> afterConsultation(@PathVariable long id) {
        return new ResponseEntity<>(bookingService.afterConsultationDone(id), HttpStatus.OK);
    }
}
