package com.nbr.trp.ledger.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "ledger")
public class Ledger {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uniqueidentifier default newid()")
    public String lid;

    @Column(name = "taxpayer_id",nullable = false)
    public String taxpayerId;

    @Column(name = "paid_amount",nullable = false)
    public String paidAmount;

    @Column(name = "minimum_tax",nullable = false)
    public String minimumTax;

    @Column(name = "payment_rule",nullable = false)
    public String paymentRule;

    @Column(name = "payment_method",nullable = false)
    public String paymentMethod;

    @Column(name = "transaction_id")
    public String transactionId;

    @Column(name = "challan_id")
    public String challanId;

    @Column(name = "assessment_year",nullable = false)
    public String assessmentYear;

    @Column(name = "agent_tin",nullable = false)
    public String agentTin;

    @Column(name = "representative_tin",nullable = false)
    public String representativeTin;

    @Column(name = "year_no")
    public String yearNo;

    @Column(name = "remarks")
    public String remarks;

    @Column(name = "created_at")
    @CreationTimestamp
    public Timestamp created_at;

    public Ledger(String lid, String taxpayerId, String paidAmount, String minimumTax, String paymentRule, String paymentMethod, String transactionId, String challanId, String assessmentYear, String agentTin, String representativeTin, String yearNo, String remarks, Timestamp created_at) {
        this.lid = lid;
        this.taxpayerId = taxpayerId;
        this.paidAmount = paidAmount;
        this.minimumTax = minimumTax;
        this.paymentRule = paymentRule;
        this.paymentMethod = paymentMethod;
        this.transactionId = transactionId;
        this.challanId = challanId;
        this.assessmentYear = assessmentYear;
        this.agentTin = agentTin;
        this.representativeTin = representativeTin;
        this.yearNo = yearNo;
        this.remarks = remarks;
        this.created_at = created_at;
    }
}
