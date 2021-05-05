package com.ogya.AnnisaFirdaus.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.Date;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull(message = "First Name is mandatory")
	private String firstName;
	
	@NotNull(message = "Last Name is mandatory")
	private String lastName;
	
	@NotNull(message = "Age is mandatory")
	private String age;
	
	@NotNull(message = "Email is mandatory")
	private String email;
	
	@NotNull(message = "Mobile Number is mandatory")
	private String mobileNumber;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateStarted;
	
	@NotNull(message = "Status is mandatory")
	private int status;
	
	@NotNull(message = "Gender is mandatory")
	private Boolean gender;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Date getDateStarted() {
		return dateStarted;
	}

	public void setDateStarted(Date dateStarted) {
		this.dateStarted = dateStarted;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	
	
}
