package com.nbr.trp.representative.service;

import com.nbr.trp.representative.entity.AssessmentYear;
import com.nbr.trp.representative.repository.YearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YearServiceImpl implements  YearService{

    @Autowired
    YearRepository yearRepository;

    @Override
    public AssessmentYear saveyear(String year) {
        AssessmentYear yr= new AssessmentYear();
        yr.setYear(year);
        return yearRepository.save(yr);
    }

    @Override
    public AssessmentYear findYear(String year) {
        return yearRepository.findByYearOrderByCreatedDesc(year);
    }
}
