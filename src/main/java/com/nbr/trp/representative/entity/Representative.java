package com.nbr.trp.representative.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "representative")
public class Representative {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uniqueidentifier default newid()")
    public String uuid;

    @Column(name = "Name",nullable = false)
    public String Name;

    @Column(name = "Agentid",nullable = false)
    public String Agentid;

    @Column(name = "Username",nullable = false)
    public String Username;

    @Column(name = "DOB")
    public Date DOB;

    @Column(name = "MobileNo")
    public String MobileNo;

    @Column(name = "NID")
    public String NID;

    @Column(name = "BusinessAddressId")
    public int BusinessAddressId;

    @Column(name = "CurrentAddressId")
    public int CurrentAddressId;

    @Column(name = "PermanentAddressId")
    public int PermanentAddressId;

    @Column(name = "BankAccountName")
    public String BankAccountName;

    @Column(name = "BankAccountNo")
    public String BankAccountNo;

    @Column(name = "BankName")
    public String BankName;

    @Column(name = "BankBranch")
    public String BankBranch;

    @Column(name = "RoutingNo")
    public String RoutingNo;

    public Representative(String uuid, String name, String agentid, String username, Date DOB, String mobileNo, String NID, int businessAddressId, int currentAddressId, int permanentAddressId, String bankAccountName, String bankAccountNo, String bankName, String bankBranch, String routingNo) {
        this.uuid = uuid;
        Name = name;
        Agentid = agentid;
        Username = username;
        this.DOB = DOB;
        MobileNo = mobileNo;
        this.NID = NID;
        BusinessAddressId = businessAddressId;
        CurrentAddressId = currentAddressId;
        PermanentAddressId = permanentAddressId;
        BankAccountName = bankAccountName;
        BankAccountNo = bankAccountNo;
        BankName = bankName;
        BankBranch = bankBranch;
        RoutingNo = routingNo;
    }
}
