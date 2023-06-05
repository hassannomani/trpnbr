package com.nbr.trp.ledger.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "metrics")
public class Metrics {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uniqueidentifier default newid()")
    public String mid;

    @Column(name = "assessment_year",nullable = false)
    public String assessmentYear;

    @Column(name = "minimum_tax",nullable = false)
    public Double minimumTax;

    @Column(name = "tax_amount",nullable = false)
    public Double taxAmount;

    @Column(name = "agent_rate",nullable = false)
    public Double agentRate;

    @Column(name = "representative_rate",nullable = false)
    public Double representativeRate;

    @Column(name = "year_no",nullable = false)
    public Double yearNo;

    public Metrics(String mid, String assessmentYear, Double minimumTax, Double taxAmount, Double agentRate, Double representativeRate, Double yearNo) {
        this.mid = mid;
        this.assessmentYear = assessmentYear;
        this.minimumTax = minimumTax;
        this.taxAmount = taxAmount;
        this.agentRate = agentRate;
        this.representativeRate = representativeRate;
        this.yearNo = yearNo;
    }
}
