package com.group7.swp391.drug_prevention.domain.response;

import com.group7.swp391.drug_prevention.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
public class ResBookingDTO {
    private long id;
    private Instant bookingTime;
    private String status;
    private User consultant;
    private User member;

    public ResBookingDTO(long id, Instant bookingTime, String status, User consultant) {
        this.id = id;
        this.bookingTime = bookingTime;
        this.status = status;
        this.consultant = consultant;
    }

    public ResBookingDTO(long id, User member, String status, Instant bookingTime, User consultant) {
        this.id = id;
        this.member = member;
        this.status = status;
        this.bookingTime = bookingTime;
        this.consultant = consultant;
    }
}
