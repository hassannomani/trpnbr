package com.nbr.trp.submission.repository;

import com.nbr.trp.submission.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface SubmissionRepository extends JpaRepository<Submission, String> {
    public Submission findByRuid(String id);

    public List<Submission> findAll();

    public Submission save(Submission submission);

    public Submission findByRTin(String rtin);


//    @Query(value = "select * from calculations c where c.username = ?1 and c.assessment_year = ?2 and c.submitted = ?3",
//            nativeQuery = true)
//    public Submission findByUsernameAndAssessmentYearAndSubmitted(String username, String assmntYear, String submitted);
}
