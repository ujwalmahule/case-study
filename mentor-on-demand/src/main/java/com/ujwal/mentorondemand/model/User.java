package com.ujwal.mentorondemand.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ujwal.mentorondemand.validator.ValidateMentor;

@ValidateMentor
@Entity
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;

	@Temporal(TemporalType.DATE)
	@Column(name = "dob")
	private Date dob;
	
	@Column(name = "mobild")
	private String mobile;
	
	@Column(name = "address_line1")
	private String addressLine1;
	
	@Column(name = "address_line2")
	private String addressLine2;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "pin")
	private String pin;
	
	@Column(name = "active")
	private boolean active;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private UserRole userRole;
	
	@OneToOne(mappedBy = "userProfile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Mentor mentor; 

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@JsonIgnore
	private List<Payment> payments;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public Mentor getMentor() {
		return mentor;
	}

	public List<Payment> getPayments() {
		return payments;
	}

}
