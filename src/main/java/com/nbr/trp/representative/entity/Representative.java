package com.nbr.trp.representative.entity;

import com.nbr.trp.agent.entity.Agent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "representative")
public class Representative {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uniqueidentifier default newid()")
    public String uuid;

    @Column(name = "name",nullable = false)
    public String name;

    @Column(name = "agent_id",nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agent_id")
    public Agent agentId;

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
}

