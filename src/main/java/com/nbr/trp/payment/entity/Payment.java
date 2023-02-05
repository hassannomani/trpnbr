package com.nbr.trp.payment.entity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "payments")
@NoArgsConstructor

public class Payment {
    @Id   //BINARY(16)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uniqueidentifier  default newid()")
    private String uuid;

    @NonNull
    @Column(nullable = false,name = "username")
    private String username;

    @NonNull
    @Column(nullable = false)
    private double amount;

    @NonNull
    @Column(nullable = false,name = "assessment_year")
    private String assessmentYear;

    @NonNull
    @Column(nullable = false)
    private String getway;

    @NonNull
    @Column(nullable = false,length = 11)
    private String mobile;

    @NonNull
    @Column(nullable = false,name = "transaction_id")
    private String transactionId;


    @Column(name = "created_at")
    @CreationTimestamp
    private Date created_at;

    @Column(nullable = false, name = "agent_id")
    private String agentId;

    @Column(nullable = false, name = "representative_id")
    private String representativeId;



    public Payment(String uuid, String tin, String assessmentYear, String getway, String mobile, double amount, String transactionId, String agentId, String representativeId) {
        this.uuid = uuid;
        this.username = tin;
        this.assessmentYear = assessmentYear;
        this.getway = getway;
        this.mobile = mobile;
        this.amount = amount;
        this.transactionId = transactionId;
        this.agentId = agentId;
        this.representativeId = representativeId;
    }
}

