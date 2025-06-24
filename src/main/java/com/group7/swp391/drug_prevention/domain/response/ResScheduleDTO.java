package com.group7.swp391.drug_prevention.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResScheduleDTO {
    private Instant startTime;
    private Instant endTime;

}
