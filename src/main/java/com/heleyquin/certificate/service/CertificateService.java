package com.heleyquin.certificate.service;

import com.heleyquin.certificate.entity.Certificate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CertificateService {
    Page<Certificate> getCertificates(Pageable pageable);
    Certificate getCertificateById(int id);
    Certificate getCertificateByName(String name);
    Certificate createCertificate(Certificate certificate);
    boolean updateCertificate(int id, Certificate certificate);
    boolean deleteCertificate(int id);
}
