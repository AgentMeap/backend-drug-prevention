package com.group7.swp391.drug_prevention.repository;

import com.group7.swp391.drug_prevention.domain.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificateRepository extends JpaRepository<Certificate,Long> {
    List<Certificate> findByConsultantId(long consultantId);
}
