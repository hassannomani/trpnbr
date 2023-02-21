package com.nbr.trp.agent.entity;

import com.nbr.trp.common.entity.Address;
import com.nbr.trp.common.entity.BankInformationDetails;
import com.nbr.trp.user.entity.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "agent")
public class Agent {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(columnDefinition = "uniqueidentifier default newid()")
	public String uuid;

	@Column(name = "name",nullable = false)
	public String name;

	@Column(name = "username",nullable = false)
	public String username;

	@Column(name = "phone")
	public String phone;

	@Column(name = "registration_type",nullable = false)
	public String registrationType;

	@Column(name = "registration_no",nullable = false)
	public String registrationNo;

	@Column(name = "registration_date",nullable = false)
	public Date registrationDate;

	@Column(name = "contact_person",nullable = false)
	public String contactPerson;

	@Column(name = "contact_number",nullable = false)
	public String contactNumber;

	@Column(name = "contact_email",nullable = false)
	public String contactEmail;

//	@Column(name = "business_address_id")
//	public int businessAddressId;

//	@Column(name = "current_address_id")
//	public int currentAddressId;

//	@Column(name = "permanent_address_id")
//	public int permanentAddressId;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "agent_bankinformation",
			joinColumns = @JoinColumn(name = "agent_id"),
			inverseJoinColumns = @JoinColumn(name = "bank_id")
	)
	private Set<BankInformationDetails> bankinformation = new HashSet<>();
//	@Column(name = "bank_information_id")
//	public String bankInformationId;


	@Column(name = "dob")
	public Date dob;

	@Column(name = "father_name")
	public String fatherName;

	@Column(name = "mother_name")
	public String motherName;

	@Column(name = "spouse_name")
	public String spouseName;

	@Column(name = "mobile_no")
	public String mobileNo;

	@Column(name = "reg_ass_nid")
	public String regAssNID;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "agent_address",
			joinColumns = @JoinColumn(name = "agent_id"),
			inverseJoinColumns = @JoinColumn(name = "address_id")
	)
	private Set<Address> address = new HashSet<>();

//	@ManyToMany


	public Agent(String uuid, String name, String username, String phone, String registrationType, String regNo, String contactPerson, String contactNumber, String contactEmail/*, int businessAddressId, int currentAddressId, int permanentAddressId*/, Set<BankInformationDetails> bank, String fatherName, String motherName, String spouseName, String mobileNo, String regAssNID, Date regDate, Date dob, Set<Address> address) {
		this.uuid = uuid;
		this.name = name;
		this.username = username;
		this.phone = phone;
		this.registrationType = registrationType;
		this.registrationNo = regNo;
		this.contactPerson = contactPerson;
		this.contactNumber = contactNumber;
		this.contactEmail = contactEmail;
//		this.businessAddressId = businessAddressId;
//		this.currentAddressId = currentAddressId;
//		this.permanentAddressId = permanentAddressId;
		this.bankinformation = bank;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.spouseName = spouseName;
		this.mobileNo = mobileNo;
		this.regAssNID = regAssNID;
		this.registrationDate = regDate;
		this.dob = dob;
		this.address = address;
	}
}
