package com.heleyquin.certificate.repo;

import java.util.Optional;

import com.heleyquin.certificate.entity.Certificate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepo extends JpaRepository<Certificate, Integer> {
    Optional<Certificate> findByName(String name);
}
