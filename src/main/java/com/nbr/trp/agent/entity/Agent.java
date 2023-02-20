package com.nbr.trp.agent.entity;

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

	@Column(name = "reg_no",nullable = false)
	public String regNo;

	@Column(name = "reg_date",nullable = false)
	public Date regDate;

	@Column(name = "contact_person",nullable = false)
	public String contactPerson;

	@Column(name = "contact_number",nullable = false)
	public String contactNumber;

	@Column(name = "contact_email",nullable = false)
	public String contactEmail;

	@Column(name = "business_address_id")
	public int businessAddressId;

	@Column(name = "current_address_id")
	public int currentAddressId;

	@Column(name = "permanent_address_id")
	public int permanentAddressId;

	@Column(name = "bank_information_id")
	public String bankInformationId;


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

//	@ManyToMany
//	@JoinTable(
//			name = "course_like",
//			joinColumns = @JoinColumn(name = "student_id"),
//			inverseJoinColumns = @JoinColumn(name = "course_id"))
//	private Set<Role> roles = new HashSet<>();

	public Agent(String uuid, String name, String username, String phone, String registrationType, String regNo,  String contactPerson, String contactNumber, String contactEmail, int businessAddressId, int currentAddressId, int permanentAddressId, String bankInformationId, String fatherName, String motherName, String spouseName, String mobileNo, String regAssNID,Date regDate, Date dob) {
		this.uuid = uuid;
		this.name = name;
		this.username = username;
		this.phone = phone;
		this.registrationType = registrationType;
		this.regNo = regNo;
		this.contactPerson = contactPerson;
		this.contactNumber = contactNumber;
		this.contactEmail = contactEmail;
		this.businessAddressId = businessAddressId;
		this.currentAddressId = currentAddressId;
		this.permanentAddressId = permanentAddressId;
		this.bankInformationId = bankInformationId;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.spouseName = spouseName;
		this.mobileNo = mobileNo;
		this.regAssNID = regAssNID;
		this.regDate = regDate;
		this.dob = dob;
	}
}
