package com.nbr.trp.representative.entity;

import com.nbr.trp.common.entity.Address;
import com.nbr.trp.common.entity.BankInformationDetails;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "representative")
public class Representative {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uniqueidentifier default newid()")
    public String userid;

    @Column(name = "re_name",nullable = false)
    public String reName;

    @Column(name = "agent_id",nullable = false)
    public String agentId;

    @Column(name = "tin_no",nullable = false)
    public String tinNo;

    @Column(name = "re_dob")
    public Date reDob;

    @Column(name = "re_mobile_no")
    public String reMobileNo;

    @Column(name = "nid")
    public String nid;

    @Column(name="trp_id", nullable = false)
    public String trpId;

    @Column(name="ref_no", nullable = false)
    public String refNo;

    @Column(name="file_path", nullable = false)
    public String filePath;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "representative_address",
            joinColumns = @JoinColumn(name = "representative_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private Set<Address> re_address = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "representative_bankinformation",
            joinColumns = @JoinColumn(name = "representative_id"),
            inverseJoinColumns = @JoinColumn(name = "bank_id")
    )
    private Set<BankInformationDetails> re_bankinformation = new HashSet<>();


    public Representative(String uuid, String name, String agentId, String tin, Date dob, String mobileNo, String nid, Set<Address> address, Set<BankInformationDetails> bank, String trpid, String refNo,String filepath ) {
        this.userid = uuid;
        this.reName = name;
        this.agentId = agentId;
        this.tinNo = tin;
        this.reDob = dob;
        this.reMobileNo = mobileNo;
        this.nid = nid;
        this.re_address = address;
        this.re_bankinformation = bank;
        this.trpId = trpid;
        this.refNo = refNo;
        this.filePath = filepath;
    }
}

