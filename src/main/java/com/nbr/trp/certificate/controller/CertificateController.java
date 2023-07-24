package com.nbr.trp.certificate.controller;

import com.nbr.trp.certificate.entity.Certificate;
import com.nbr.trp.certificate.service.CertificateService;
import com.nbr.trp.ledger.entity.Ledger;
import com.nbr.trp.user.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/certificate")
public class CertificateController {

    @Autowired
    CertificateService certificateService;

    @PostMapping("/bulk")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveCertificates(@RequestBody List<Certificate> certificates){
        try{
            certificateService.saveCertificateBulk(certificates);
            return ResponseEntity.ok(new MessageResponse("success"));

        } catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @PostMapping("/duplicacy")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> checkDuplicateCertificates(@RequestBody List<String> tin){
        try{
            List<Certificate> certificateList = certificateService.checkDuplicacy(tin);
            return ResponseEntity.ok(certificateList);

        } catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/check/{tin}/{nid}")
    public ResponseEntity<?> checkCertificate(@PathVariable String tin, @PathVariable String nid){
        try{
            Boolean certificate = certificateService.getCertificateByTinNNid(tin, nid);
            System.out.println("cert"+certificate);
            return ResponseEntity.ok(certificate);

        } catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }
}
