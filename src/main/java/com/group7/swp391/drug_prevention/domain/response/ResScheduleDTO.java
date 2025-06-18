package com.group7.swp391.drug_prevention.domain.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class ResScheduleDTO {
    private Instant startTime;
    private Instant endTime;
    private String dayOfWeek;

}
