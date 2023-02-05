package com.nbr.trp.calculation.service;

import com.nbr.trp.calculation.entity.Calculation;
import com.nbr.trp.calculation.exception.CalculationNotFoundException;
import com.nbr.trp.calculation.repository.CalculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculationServiceImpl implements CalculationService{
    @Autowired
    private CalculationRepository calculationRepository;
    @Autowired
    public void CalculationServiceImpl( CalculationRepository calculationRepository){
        this.calculationRepository = calculationRepository;
    }

    public Calculation exceptionHandler(Calculation calculation) throws CalculationNotFoundException {
        if(calculation==null)
            throw new CalculationNotFoundException("Calculation not found");
        else return calculation;
    }
    public Calculation getCalculationByUuid(String id) throws CalculationNotFoundException {
        Calculation calculation = calculationRepository.findByUuid(id);
        return exceptionHandler(calculation);
    }
    public Calculation getCalculationByUsername(String username) throws CalculationNotFoundException {
        Calculation calculation = calculationRepository.findByUsername(username);
        return exceptionHandler(calculation);
    }
    public List<Calculation> getAllCalculation(){
        return calculationRepository.findAll();
    }
    public Calculation updateCalculationByUsername(String username, Calculation calculation) throws CalculationNotFoundException {
        Calculation calculation1 = calculationRepository.findByUsername(username);

        if(calculation1!=null){
            calculation1.setGender(calculation.getGender());
            calculation1.setSalary(calculation.getSalary());
            calculation1.setInvestment(calculation.getInvestment());
            //calculation1.setTin(calculation1.getSalary());
            calculation1.setFestivalBonus(calculation.getFestivalBonus());
            calculation1.setSourceTax(calculation.getSourceTax());
            calculation1.setHouseRent(calculation.getHouseRent());
            calculation1.setAssessmentYear(calculation.getAssessmentYear());
            calculation1.setAmount(calculation.getAmount());
            calculation1.setSubmitted(calculation.getSubmitted());
            calculation1.setUsername(calculation.getUsername());
            calculation1.setAgentId(calculation.getAgentId());
            calculation1.setRepresentativeId(calculation.getRepresentativeId());
            Calculation c =calculationRepository.save(calculation1);

            return c;
        }else{
            return exceptionHandler(calculation1);
        }

    }

    public Calculation getCalculationByUsernameNAssessmentYear(String tin, String year) {
        Calculation calculation = calculationRepository.findByUsernameAndAssessmentYear(tin,year);
        return calculation;
    }

    public Calculation getCalculationByUsernameNAssessmentYearNSubmitted(String tin, String year) {
        Calculation calculation = calculationRepository.findByUsernameAndAssessmentYearAndSubmitted(tin,year,"true");
        return calculation;
    }

    public Calculation saveCalculation(Calculation calculation) throws CalculationNotFoundException {

        double amount= calculateTax(calculation);
        calculation.setAmount(String.valueOf(amount));
        Calculation calc = getCalculationByUsernameNAssessmentYear(calculation.getUsername(),calculation.getAssessmentYear());
        if(calc==null){
            calculationRepository.save(calculation);
            return calculation;
        }else{
            try{
                Calculation cal = updateCalculationByUsername(calculation.getUsername(),calculation);
                return cal;
            }catch (Exception e){
                throw new CalculationNotFoundException();
            }

        }

    }

    private double maleTaxCalculate(double totalTax){

        double payableAmount =0;
        if(totalTax<=300000)
            payableAmount = 0;

        else if(totalTax>300000 && totalTax<=400000)
            payableAmount = 0.05*(totalTax-300000);

        else if(totalTax>400000 && totalTax<=700000)
            payableAmount=(0.1*(totalTax-400000))+(0.05*100000);

        else if(totalTax>700000 && totalTax<=1100000)
            payableAmount=(0.15*(totalTax-700000))+(0.1*300000)+(0.05*100000);

        else if(totalTax>1100000 && totalTax<=1600000)
            payableAmount=(0.2*(totalTax-1100000))+(0.15*400000)+(0.1*300000)+(0.05*100000);

        else
            payableAmount=(0.25*(totalTax-1600000))+(0.2*500000)+(0.15*400000)+(0.1*300000)+(0.05*100000);
        return payableAmount;
    }

    private double femaleTaxCalculate(double totalTax){

        double payableAmount=0;

        if(totalTax<=350000)
            payableAmount = 0;

        else if(totalTax>350000 && totalTax<=450000)
            payableAmount = 0.05*(totalTax-350000);

        else if(totalTax>450000 && totalTax<=750000)
            payableAmount=(0.1*(totalTax-450000))+(0.05*100000);

        else if(totalTax>750000 && totalTax<=1150000)
            payableAmount=(0.15*(totalTax-750000))+(0.1*300000)+(0.05*100000);

        else if(totalTax>1150000 && totalTax<=1650000)
            payableAmount=(0.2*(totalTax-1150000))+(0.15*400000)+(0.1*300000)+(0.05*100000);

        else
            payableAmount=(0.25*(totalTax-1650000))+(0.2*500000)+(0.15*400000)+(0.1*300000)+(0.05*100000);

        return payableAmount;
    }
    private double calculateTax(Calculation calculation) {
        double baseSalary = Double.parseDouble(calculation.getSalary());
        double festiveNSalaryTax = baseSalary+Double.parseDouble(calculation.getFestivalBonus());
        double baseHouseRent = Double.parseDouble(calculation.getHouseRent());
        double primaryHouseRent = baseSalary*0.5;
        double smallerOne = primaryHouseRent>300000?300000:primaryHouseRent;
        double houseRentTax = baseHouseRent-smallerOne>0?baseHouseRent-smallerOne:0;
        double totalTax = festiveNSalaryTax+houseRentTax;
        double investment = Double.parseDouble(calculation.getInvestment());
        double investmentRebate= 0;
        if(totalTax>1500000)
            investmentRebate = investment*0.1;
        else
            investmentRebate = investment*0.15;
        double payableAmount=0;
        String gender = calculation.getGender();
//
        if(gender=="male"){
            payableAmount = maleTaxCalculate(totalTax);
        }else{
            payableAmount = femaleTaxCalculate(totalTax);
        }

        double finalPayableTax = payableAmount - investmentRebate ;
        if(finalPayableTax<5000)
            finalPayableTax = 5000- Double.parseDouble(calculation.getSourceTax());
        return finalPayableTax;
    }
}
