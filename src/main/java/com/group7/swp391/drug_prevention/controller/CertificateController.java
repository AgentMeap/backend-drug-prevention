package com.group7.swp391.drug_prevention.controller;

import com.group7.swp391.drug_prevention.domain.Certificate;
import com.group7.swp391.drug_prevention.domain.request.ReqCertificateDTO;
import com.group7.swp391.drug_prevention.domain.response.ResCertificateDTO;
import com.group7.swp391.drug_prevention.service.CertificateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/certificate")
public class CertificateController {
    private final CertificateService certificateService;
    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping("/getAll")
    public List<Certificate> getAllCertificate() {
        return certificateService.getCertificate();
    }

    @PostMapping("/create")
    public Certificate createCertificate(@RequestBody ReqCertificateDTO dto) {
        return certificateService.createCertificate(dto);
    }

    @GetMapping("/findByConsultantId/{consultantId}")
    public List<ResCertificateDTO>  findCertificateByConsultantId(@PathVariable long consultantId) {
        return certificateService.getCertificateByConsultantId(consultantId);
    }

}
