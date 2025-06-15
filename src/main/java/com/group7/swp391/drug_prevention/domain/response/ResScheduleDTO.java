package com.group7.swp391.drug_prevention.domain.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class ResScheduleDTO {
    private LocalTime startTime;
    private LocalTime endTime;
    private String dayOfWeek;

}
