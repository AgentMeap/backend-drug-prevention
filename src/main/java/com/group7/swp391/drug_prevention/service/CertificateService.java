package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.Certificate;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.domain.request.ReqCertificateDTO;
import com.group7.swp391.drug_prevention.domain.response.ResCertificateDTO;
import com.group7.swp391.drug_prevention.repository.CertificateRepository;
import com.group7.swp391.drug_prevention.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CertificateService {
    private final CertificateRepository certificateRepository;
    private final UserRepository userRepository;

    public CertificateService(CertificateRepository certificateRepository, UserRepository userRepository) {
        this.certificateRepository = certificateRepository;
        this.userRepository = userRepository;
    }

    public List<Certificate> getCertificate() {
        return certificateRepository.findAll();
    }

    public Certificate createCertificate(ReqCertificateDTO dto){
        Certificate certificate = new Certificate();
        User consultant = userRepository.getOne(dto.getConsultantId());

        certificate.setName(dto.getName());
        certificate.setIssuer(dto.getIssuer());
        certificate.setIssueDate(dto.getIssueDate());
        certificate.setExpiryDate(dto.getExpiryDate());
        certificate.setConsultant(consultant);

        certificateRepository.save(certificate);

        return certificate;
    }

    public List<ResCertificateDTO> getCertificateByConsultantId(long consultantId) {
            return certificateRepository.findByConsultantId(consultantId).stream().map(certificate -> new ResCertificateDTO(
                    certificate.getName(),
                    certificate.getIssuer(),
                    certificate.getIssueDate(),
                    certificate.getExpiryDate(),
                    certificate.getConsultant()
            )).toList();
    }
}
