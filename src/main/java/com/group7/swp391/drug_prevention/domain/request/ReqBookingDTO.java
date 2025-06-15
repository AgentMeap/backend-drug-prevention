package com.group7.swp391.drug_prevention.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class ReqBookingDTO {
    private long memberId;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime bookingTime;

}
