package com.group7.swp391.drug_prevention.domain.response.file;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.group7.swp391.drug_prevention.domain.Event;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.util.constant.EventRegistrationStatus;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
public class ResEventRegistrationDTO {
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;
    private String location;

    private String memberName;
    private String phoneNumber;
    private String email;

    private Instant registrationDate;
    private EventRegistrationStatus status;
}
