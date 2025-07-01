package com.group7.swp391.drug_prevention.domain.response;

import com.group7.swp391.drug_prevention.domain.User;
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
public class ResCertificateDTO {
    private String name;
    @Column(name = "issuer",columnDefinition = "NVARCHAR(250)")
    private String issuer;
    private Instant issueDate;
    private Instant expiryDate;
    private User consultant;

}
