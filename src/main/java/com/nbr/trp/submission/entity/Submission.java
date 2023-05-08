package com.nbr.trp.submission.entity;

import com.nbr.trp.common.entity.Address;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "submissions")
@NoArgsConstructor
public class Submission {
    @Id   //BINARY(16)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uniqueidentifier  default newid()")
    private String ruid;

    @NonNull
    @Column(nullable = false, name="returnee_tin")
    private String returneeTin;

    @NonNull
    @Column(nullable = false, name="returnee_nid")
    private String returneeNid;

    @NonNull
    @Column(nullable = false, name="r_mobile")
    private String returneeMobile;

    @NonNull
    @Column(nullable = false, name="returnee_name")
    private String returneeName;

    @NonNull
    @Column(nullable = false, name="returnee_father")
    private String returneeFather;

    @NonNull
    @Column(nullable = false, name="returnee_spouse")
    private String returneeSpouse;

    @NonNull
    @Column(nullable = false, name="assessment_year")
    private String assessmentYear;

    @NonNull
    @Column(nullable = false, name="returnee_circle")
    private String returneeCircle;

    @NonNull
    @Column(nullable = false, name="returnee_zone")
    private String returneeZone;

    @NonNull
    @Column(nullable = false, name="returnee_residency")
    private String returneeResidency;

    @NonNull
    @Column(nullable = false, name = "taxable_income")
    private String taxableIncome;

    @NonNull
    @Column(nullable = false, name = "gross_wealth")
    private String grossWealth;

    @NonNull
    @Column(nullable = false, name = "source_income")
    private String sourceIncome;

    @NonNull
    @Column(nullable = false, name = "returnee_gender")
    private String returneeGender;

    @NonNull
    @Column(nullable = false, name = "submitted")
    private String submitted;

    @NonNull
    @Column(nullable = false, name = "payment_method")
    private String paymentMethod;

    @NonNull
    @Column(nullable = false, name = "payment_no")
    private String paymentNo;

    @NonNull
    @Column(nullable = false, name = "payment_date")
    private String paymentDate;

    @NonNull
    @Column(nullable = false, name = "agent_id")
    private String agentId;

    @NonNull
    @Column(nullable = false, name = "representative_id")
    private String representativeId;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "returnee_address",
            joinColumns = @JoinColumn(name = "submission_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private Set<Address> returnee_address = new HashSet<>();

    public Submission(String ruid, String returneeTin, String returneeNid, String returneeMobile, String returneeName, String returneeFather, String returneeSpouse, String assessmentYear, String returneeCircle, String returneeZone, String returneeResidency, String taxableIncome, String grossWealth, String sourceIncome,  String gender, String submitted, String paymentMethod, String paymentNo, String paymentDate,  String agentId,  String representativeId, Set<Address> re_address) {
        this.ruid = ruid;
        this.returneeTin = returneeTin;
        this.returneeNid = returneeNid;
        this.returneeMobile = returneeMobile;
        this.returneeName = returneeName;
        this.returneeFather = returneeFather;
        this.returneeSpouse = returneeSpouse;
        this.assessmentYear = assessmentYear;
        this.returneeCircle = returneeCircle;
        this.returneeZone = returneeZone;
        this.returneeResidency = returneeResidency;
        this.taxableIncome = taxableIncome;
        this.grossWealth = grossWealth;
        this.sourceIncome = sourceIncome;
        this.returneeGender = gender;
        this.submitted = submitted;
        this.paymentMethod = paymentMethod;
        this.paymentNo = paymentNo;
        this.paymentDate = paymentDate;
        this.agentId = agentId;
        this.representativeId = representativeId;
        this.returnee_address = re_address;
    }
}
