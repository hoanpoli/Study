package com.example.test.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.example.test.common.Const;

@Entity
@Table(name = "user", schema = "public")
public class Users {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq_generator")
	@SequenceGenerator(name = "user_id_seq_generator", sequenceName = "public.user_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Column(columnDefinition = "varchar(64)")
	private String userName;

	@Column(columnDefinition = "varchar(128)")
	private String email;

	@Column(columnDefinition = "varchar(64)")
	private String accountNo;

	@Column(columnDefinition = "varchar(32)")
	private String firstName;

	@Column(columnDefinition = "varchar(32)")
	private String lastName;

	@Column(columnDefinition = "varchar(16)")
	private String contactNo;

	@Column(columnDefinition = "varchar(128)")
	private String remarks;

	@Column(columnDefinition = "char(3)")
	private String status;

	@Type(type = "pg-uuid")
	private UUID uuid;

	@Column(columnDefinition = "varchar(256)")
	private String passwordHash;

	@Column(columnDefinition = "bool")
	private boolean isDeleted;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	// end

	// region -- Methods --

	public Users() {
		this.status = Const.STATUS_ACTIVE;
	}

	// end
}