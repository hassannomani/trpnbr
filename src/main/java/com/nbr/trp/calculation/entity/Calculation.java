package com.nbr.trp.calculation.entity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "calculations")
@NoArgsConstructor
public class Calculation {
    @Id   //BINARY(16)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uniqueidentifier  default newid()")
    private String uuid;

    @NonNull
    @Column(nullable = false, name="assessment_year")
    private String assessmentYear;

    @NonNull
    @Column(nullable = false, name = "salary")
    private String salary;

    @NonNull
    @Column(nullable = false, name = "house_rent")
    private String houseRent;

    @NonNull
    @Column(nullable = false, name = "source_tax")
    private String sourceTax;

    @NonNull
    @Column(nullable = false, name = "festival_bonus")
    private String festivalBonus;

    @NonNull
    @Column(nullable = false, name = "investment")
    private String investment;

    @NonNull
    @Column(nullable = false, name = "gender")
    private String gender;

    @NonNull
    @Column(nullable = false, name = "username")
    private String username;

    @NonNull
    @Column(nullable = false, name = "amount")
    private String amount;

    @NonNull
    @Column(nullable = false, name = "submitted")
    private String submitted;


    public Calculation(String uuid, String assessmentYear, String salary, String houseRent, String sourceTax, String festivalBonus,  String investment, String gender, String amount, String tin,String submitted) {
        this.uuid = uuid;
        this.assessmentYear = assessmentYear;
        this.salary = salary;
        this.houseRent = houseRent;
        this.sourceTax = sourceTax;
        this.festivalBonus = festivalBonus;
        this.investment = investment;
        this.gender = gender;
        this.username = tin;
        this.amount = amount;
        this.submitted = submitted;
    }
}
