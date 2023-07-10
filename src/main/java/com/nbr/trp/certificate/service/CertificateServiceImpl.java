package com.nbr.trp.certificate.service;

import com.nbr.trp.certificate.entity.Certificate;
import com.nbr.trp.certificate.repository.CertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService {

    @Autowired
    CertificateRepository certificateRepository;

    @Override
    public Certificate saveCertificate(Certificate certificate) {
        return null;
    }

    @Override
    public void saveCertificateBulk(List<Certificate> certificates) {
         certificateRepository.saveAll(certificates);
    }

    @Override
    public List<Certificate> getAllCertificates() {
        return null;
    }

    @Override
    public Certificate getCertifcateByTin(String tin) {
        return null;
    }

    @Override
    public Certificate getUserById(String id) {
        return null;
    }

    @Override
    public List<Certificate> checkDuplicacy(List<String> tin){
        //String tins = String.join(",", tin);
        //System.out.println(tins);
        return certificateRepository.checkDuplicacy(tin);
    }
}
