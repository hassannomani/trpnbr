package com.nbr.trp.agent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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


}
