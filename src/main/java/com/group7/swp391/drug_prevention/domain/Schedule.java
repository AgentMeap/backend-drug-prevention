package com.group7.swp391.drug_prevention.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import com.group7.swp391.drug_prevention.util.constant.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "schedules")
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "consultantId")
    private User consultant;

    private Instant startTime;
    private Instant endTime;



    private String dayOfWeek;

}
