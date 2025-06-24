package com.group7.swp391.drug_prevention.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReqScheduleDTO {
    private long consultantId;
    private Instant startTime;
    private Instant endTime;
}
