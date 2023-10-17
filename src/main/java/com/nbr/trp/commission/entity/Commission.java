package com.nbr.trp.commission.entity;

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
@Table(name = "commission")
@NoArgsConstructor
public class Commission {
    @Id   //BINARY(16)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uniqueidentifier  default newid()")
    private String creationNo;

    @Column(name = "creation_date")
    @CreationTimestamp
    private Date creationDate;

    @Column(name = "debit_code")
    private String debitCode;

    @Column(name = "credit_code")
    private String creditCode;

//    @NonNull
//    @Column(nullable = false,name = "amount")
//    private String amount;

//    @NonNull
//    @Column(nullable = false,name = "taxpayerId")
//    private String taxpayerId;

    @NonNull
    @Column(nullable = false,name = "payee")
    private String payee;

    @Column(nullable = false,name = "payee_type")
    private String payeeType;

    @Column(name = "invoice_date")
    @Temporal(TemporalType.TIMESTAMP)
    private String invoiceDate;

    @Column(name = "bill_date")
    private Date billDate;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "bill_no")
    private String billNo;

    @Column(name = "invoice_no")
    private String invoiceNo;

    @Column(name = "ledger_id")
    private String ledgerId;

    @Column(name = "status")
    private String status;

}

