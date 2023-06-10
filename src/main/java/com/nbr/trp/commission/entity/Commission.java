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

    @NonNull
    @Column(nullable = false,name = "debit_code")
    private String debitCode;

    @NonNull
    @Column(nullable = false ,name = "credit_code")
    private double creditCode;

    @NonNull
    @Column(nullable = false,name = "amount")
    private String amount;

    @NonNull
    @Column(name = "remarks")
    private String remarks;


    @Column(name = "invoice_date")
    @Temporal(TemporalType.TIMESTAMP)
    private String invoiceDate;

    @Column(name = "bill_date")
    @Temporal(TemporalType.TIMESTAMP)
    private String billDate;


    @Column(name = "payment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private String paymentDate;



    @Column(name = "bill_no")
    private String billNo;


    @Column(name = "invoice_no")
    private String invoiceNo;

    @Column(name = "payment_no")
    private String paymentNo;

    public Commission(String creation_no, Date creationDate, String debitCode, double creditCode, String amount, String remarks, String invoiceDate, String billDate, String paymentDate, String billNo, String invoiceNo, String paymentNo) {
        this.creationNo = creation_no;
        this.creationDate = creationDate;
        this.debitCode = debitCode;
        this.creditCode = creditCode;
        this.amount = amount;
        this.remarks = remarks;
        this.invoiceDate = invoiceDate;
        this.billDate = billDate;
        this.paymentDate = paymentDate;
        this.billNo = billNo;
        this.invoiceNo = invoiceNo;
        this.paymentNo = paymentNo;
    }
}

