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

    @Column(name = "re_photo")
    public String rePhoto;

    @Column(name = "nid")
    public String nid;

    @Column(name="trp_id", nullable = false)
    public String trpId;

    @Column(name="ref_no", nullable = false)
    public String refNo;

    @Column(name="cert_no", nullable = false)
    public String certNo;

    @Column(name="cert_serial", nullable = false)
    public String certSerial;

    @Column(name = "cert_pass")
    public Date certPass;

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

    public Representative(String userid, String reName, String agentId, String tinNo, Date reDob, String reMobileNo, String rePhoto, String nid, String trpId, String refNo, String certNo, String certSerial, Date certPass, String filePath, Set<Address> re_address, Set<BankInformationDetails> re_bankinformation) {
        this.userid = userid;
        this.reName = reName;
        this.agentId = agentId;
        this.tinNo = tinNo;
        this.reDob = reDob;
        this.reMobileNo = reMobileNo;
        this.rePhoto = rePhoto;
        this.nid = nid;
        this.trpId = trpId;
        this.refNo = refNo;
        this.certNo = certNo;
        this.certSerial = certSerial;
        this.certPass = certPass;
        this.filePath = filePath;
        this.re_address = re_address;
        this.re_bankinformation = re_bankinformation;
    }
}

