package com.heleyquin.certificate.service;

import java.util.Optional;

import com.heleyquin.certificate.entity.Certificate;
import com.heleyquin.certificate.repo.CertificateRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    CertificateRepo certificateRepo;

    @Override
    public Page<Certificate> getCertificates(Pageable pageable) {
        return certificateRepo.findAll(pageable);
    }

    @Override
    public Certificate getCertificateById(int id) {
        return certificateRepo.findById(id).orElse(null);
    }

    @Override
    public Certificate getCertificateByName(String name) {
        return certificateRepo.findByName(name).orElse(null);
    }

    @Override
    public Certificate createCertificate(Certificate certificate) {
        return certificateRepo.save(certificate);
    }

    @Override
    public boolean updateCertificate(int id, Certificate certificate) {
        Optional<Certificate> optional = certificateRepo.findById(id);
        if (optional.isPresent()) {
            Certificate currentCertificate = optional.get();
            currentCertificate.setName(certificate.getName());
            currentCertificate.setDate(certificate.getDate());
            certificateRepo.save(currentCertificate);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCertificate(int id) {
        Optional<Certificate> optional = certificateRepo.findById(id);
        if (optional.isPresent()) {
            certificateRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
