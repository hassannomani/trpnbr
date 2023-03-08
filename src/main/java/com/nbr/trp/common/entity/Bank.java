package com.nbr.trp.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "banks")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bank_id;

    @Column(name = "bank_code")
    private String bankCode;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "dist_name")
    private String distName;

    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "routing_no")
    private String routingNo;

    public Bank(Long bank_id, String bankCode, String bankName, String distName, String branchCode, String branchName, String routingNo) {
        this.bank_id = bank_id;
        this.bankCode = bankCode;
        this.bankName = bankName;
        this.distName = distName;
        this.branchCode = branchCode;
        this.branchName = branchName;
        this.routingNo = routingNo;
    }
}
