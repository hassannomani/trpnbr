package com.nbr.trp.calculation.repository;

import com.nbr.trp.calculation.entity.Calculation;
import com.nbr.trp.representative.entity.Representative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CalculationRepository extends JpaRepository<Calculation, String> {
    public Calculation findByUuid(String id);

    public Calculation findByTin(String userid);

    public List<Calculation> findAll();

    public Calculation save(Calculation calculation);

    public Calculation findByTinAndAssessmentYear(String tin, String assmntYear);
    @Query(value = "select * from calculations c where c.tin = ?1 and c.assessment_year = ?2 and c.submitted = ?3",
            nativeQuery = true)
    public Calculation findByTinAndAssessmentYearAndSubmitted(String tin, String assmntYear, String submitted);
}
