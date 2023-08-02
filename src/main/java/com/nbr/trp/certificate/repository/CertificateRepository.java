package com.nbr.trp.certificate.repository;

import com.nbr.trp.certificate.entity.Certificate;
import com.nbr.trp.representative.entity.Representative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CertificateRepository extends JpaRepository<Certificate, String> {

    Certificate save(Certificate certificate);

    Certificate findByCertid (String id);

    Boolean existsByExamineeTinAndExamineeNid(String tin,String nid);

    List<Certificate> findAll();


    Certificate findByExamineeTinAndExamineeNid(String tin,String nid);

    @Query(value = "select * from certificates where examinee_tin in :arr ",nativeQuery = true)
    List<Certificate> checkDuplicacy(List<String> arr);
}
