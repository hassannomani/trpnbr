package com.nbr.trp.certificate.service;

import com.nbr.trp.certificate.entity.Certificate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CertificateService {

    public Certificate saveCertificate(Certificate certificate);

    public List<Certificate> getAllCertificates();

    public Certificate getCertifcateByTin(String tin);

    public Certificate getUserById(String id);
}
