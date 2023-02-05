package com.nbr.trp.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "bankinformation")
public class BankInformationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uniqueidentifier default newid()")
    public String uuid;

    @Column(name = "bank_account_name")
    public String bankAccountName;

    @Column(name = "bank_account_no")
    public String bankAccountNo;

    @Column(name = "bank_name")
    public String bankName;

    @Column(name = "bank_branch")
    public String bankBranch;

    @Column(name = "routing_no")
    public String routingNo;

    public BankInformationDetails(String uuid, String bankAccountName, String bankAccountNo, String bankName, String bankBranch, String routingNo) {
        this.uuid = uuid;
        this.bankAccountName = bankAccountName;
        this.bankAccountNo = bankAccountNo;
        this.bankName = bankName;
        this.bankBranch = bankBranch;
        this.routingNo = routingNo;
    }
}
