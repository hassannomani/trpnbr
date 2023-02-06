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

    @Column(name = "name",nullable = false)
    public String name;

    @Column(name = "agent_id",nullable = false)
    public String agentId;

    @Column(name = "username",nullable = false)
    public String username;

    @Column(name = "dob")
    public Date dob;

    @Column(name = "mobile_no")
    public String mobileNo;

    @Column(name = "nid")
    public String nid;

    @Column(name = "business_address_id")
    public int businessAddressId;

    @Column(name = "current_address_id")
    public int currentAddressId;

    @Column(name = "permanent_address_id")
    public int permanentAddressId;

    @Column(name = "bank_information_id")
    public String bankInformationId;


    public Representative(String uuid, String name, String agentId, String username, Date dob, String mobileNo, String nid, int businessAddressId, int currentAddressId, int permanentAddressId, String bankInformationId) {
        this.uuid = uuid;
        this.name = name;
        this.agentId = agentId;
        this.username = username;
        this.dob = dob;
        this.mobileNo = mobileNo;
        this.nid = nid;
        this.businessAddressId = businessAddressId;
        this.currentAddressId = currentAddressId;
        this.permanentAddressId = permanentAddressId;
        this.bankInformationId = bankInformationId;

    }
}

