package com.nbr.trp.representative.service;

import com.nbr.trp.representative.entity.AssessmentYear;
import com.nbr.trp.representative.repository.YearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface YearService {

    AssessmentYear saveyear (String year);

    AssessmentYear findYear(String year);


}
