package com.group7.swp391.drug_prevention.domain.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqCertificateDTO {
    private String name;
    @Column(name = "issuer",columnDefinition = "NVARCHAR(250)")
    private String issuer;
    private Instant issueDate;
    private Instant expiryDate;
    private long consultantId;
}
