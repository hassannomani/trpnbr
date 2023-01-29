package com.nbr.trp.agent.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

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

	@Column(name = "Name",nullable = false)
	public String Name;

	@Column(name = "Username",nullable = false)
	public String Username;

	@Column(name = "Phone")
	public String Phone;

	@Column(name = "RegistrationType",nullable = false)
	public String RegistrationType;

	@Column(name = "RegNo",nullable = false)
	public String RegNo;

	@Column(name = "RegDate",nullable = false)
	public Date RegDate;

	@Column(name = "ContactPerson",nullable = false)
	public Date ContactPerson;

	@Column(name = "ContactNumber",nullable = false)
	public Date ContactNumber;

	@Column(name = "ContactEmail",nullable = false)
	public Date ContactEmail;

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

	@Column(name = "DOB")
	public Date DOB;

	@Column(name = "FatherName")
	public String FatherName;

	@Column(name = "MotherName")
	public String MotherName;

	@Column(name = "SpouseName")
	public String SpouseName;

	@Column(name = "MobileNo")
	public String MobileNo;

	@Column(name = "RegAssNID")
	public String RegAssNID;

	public Agent(String uuid, String name, String username, String phone, String registrationType, String regNo, Date regDate, Date contactPerson, Date contactNumber, Date contactEmail, int businessAddressId, int currentAddressId, int permanentAddressId, String bankAccountName, String bankAccountNo, String bankName, String bankBranch, String routingNo, Date DOB, String fatherName, String motherName, String spouseName, String mobileNo, String regAssNID) {
		this.uuid = uuid;
		Name = name;
		Username = username;
		Phone = phone;
		RegistrationType = registrationType;
		RegNo = regNo;
		RegDate = regDate;
		ContactPerson = contactPerson;
		ContactNumber = contactNumber;
		ContactEmail = contactEmail;
		BusinessAddressId = businessAddressId;
		CurrentAddressId = currentAddressId;
		PermanentAddressId = permanentAddressId;
		BankAccountName = bankAccountName;
		BankAccountNo = bankAccountNo;
		BankName = bankName;
		BankBranch = bankBranch;
		RoutingNo = routingNo;
		this.DOB = DOB;
		FatherName = fatherName;
		MotherName = motherName;
		SpouseName = spouseName;
		MobileNo = mobileNo;
		RegAssNID = regAssNID;
	}
}
