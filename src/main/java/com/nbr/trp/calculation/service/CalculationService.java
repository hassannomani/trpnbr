package com.nbr.trp.calculation.service;

import com.nbr.trp.calculation.entity.Calculation;
import com.nbr.trp.calculation.exception.CalculationNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CalculationService {
    public Calculation getCalculationByUuid(String id) throws CalculationNotFoundException;

    public Calculation getCalculationByUsername(String id) throws CalculationNotFoundException;

    public List<Calculation> getAllCalculation();

    public Calculation updateCalculationByUsername(String id, Calculation cal) throws CalculationNotFoundException;

    public Calculation saveCalculation(Calculation calculation) throws CalculationNotFoundException;

    public Calculation getCalculationByUsernameNAssessmentYear(String tin, String assmntYear);

    public Calculation getCalculationByUsernameNAssessmentYearNSubmitted(String tin, String assmntYear);
}
