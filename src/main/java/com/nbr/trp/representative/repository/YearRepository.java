package com.nbr.trp.representative.repository;

import com.nbr.trp.representative.entity.AssessmentYear;
import com.nbr.trp.representative.entity.Representative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface YearRepository extends JpaRepository<AssessmentYear,String> {

    AssessmentYear save(String assessmentYear);

    AssessmentYear findByYearOrderByCreatedDesc(String year);

}
