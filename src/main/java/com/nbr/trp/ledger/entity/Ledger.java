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

    @Column(name = "payment_rule",nullable = false)
    public String paymentRule;

    @Column(name = "payment_method",nullable = false)
    public String paymentMethod;

    @Column(name = "transaction_id",nullable = false)
    public String transactionId;

    @Column(name = "assessment_year",nullable = false)
    public String assessmentYear;

    @Column(name = "agent_id")
    public String agentId;

    @Column(name = "agent_tin")
    public String agentTin;

    @Column(name = "representative_id")
    public String representativeId;

    @Column(name = "created_at")
    @CreationTimestamp
    public Timestamp created_at;

    public Ledger(String lid, String taxpayerId, String paidAmount, String paymentRule, String paymentMethod, String transactionId, String assessmentYear, String agentId, String agentTin, String representativeId) {
        this.lid = lid;
        this.taxpayerId = taxpayerId;
        this.paidAmount = paidAmount;
        this.paymentRule = paymentRule;
        this.paymentMethod = paymentMethod;
        this.transactionId = transactionId;
        this.assessmentYear = assessmentYear;
        this.agentId = agentId;
        this.agentTin = agentTin;
        this.representativeId = representativeId;
    }
}
