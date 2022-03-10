package com.haitoko.share.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, unique = true, length = 45)
	private String email;

	@Column(nullable = false, length = 64)
	private String password;
	
	@Column(name = "name", nullable = false, length = 45)
	private String name;
	
	@Column(name = "phone_number", nullable = false, length = 15)
	private String phoneNumber;
	
	@Column(nullable = false, length = 64)
	private String address;
	
	@Column(nullable = false, length = 45)
	private String city;
	
	@Column(nullable = false, length = 45)
	private String state;
	
	
	private boolean status;
	
	@Column(name = "created_time")
	private Date createdTime;
	

	public Customer() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	

	@Override
	public String toString() {
		return "Customer [id=" + id + ", email=" + email + ", name=" + name + "]";
	}
	
		
}
