package com.group7.swp391.drug_prevention.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group7.swp391.drug_prevention.util.constant.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "Certificate")
@Data
public class Certificate extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "issuer",columnDefinition = "NVARCHAR(250)")
    private String issuer;
    private Instant issueDate;
    private Instant expiryDate;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "consultantId")
    private User consultant;
}
