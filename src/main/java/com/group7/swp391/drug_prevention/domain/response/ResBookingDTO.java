package com.group7.swp391.drug_prevention.domain.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
@Getter
@Setter
public class ResBookingDTO {
    private Instant bookingTime;
    private String status;

}
