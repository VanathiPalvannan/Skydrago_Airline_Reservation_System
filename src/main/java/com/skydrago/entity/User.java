package com.skydrago.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames="username"))
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int id;
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	@NotNull
	@Column(unique=true)
	private long mobileNumber;
	
	@Email(message="Enter Valid Email address")
	@NotBlank
	@Column(unique=true)
	private String email;
	
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private String dob;
	
	@NotNull
	@Column(unique=true)
	private Long aadharNo;
	
	@NotBlank
	private String gender;
	
	@NotBlank
	private String userName;
	
	@NotBlank
	@Size(min=8,message="Password must be greaterthan or equal to 8 characters!")
	private String password;
	
	@NotBlank
	private String rPassword;
	
	private String role="USER";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Long getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(Long aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getrPassword() {
		return rPassword;
	}

	public void setrPassword(String rPassword) {
		this.rPassword = rPassword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
