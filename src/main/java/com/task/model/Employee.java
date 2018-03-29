package com.task.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Employee implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Date dob;
	private String dobAsString;
	private String address;
	private Date createdDate;
	private Date modifiedDate;
	private String createdDateAsString;
	private Date modifiedDateAsString;
//	@JsonDeserialize(as = Department.class)
	private Department department;
	
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
	public Date getDob() {
		return dob;
	}
	public void setDob(Date date) {
		this.dob = date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getCreatedDateAsString() {
		return createdDateAsString;
	}
	public void setCreatedDateAsString(String createdDateAsString) {
		this.createdDateAsString = createdDateAsString;
	}
	public Date getModifiedDateAsString() {
		return modifiedDateAsString;
	}
	public void setModifiedDateAsString(Date modifiedDateAsString) {
		this.modifiedDateAsString = modifiedDateAsString;
	}
	public String getDobAsString() {
		return dobAsString;
	}
	
	public void setDobAsString(String dobAsString) {
		this.dobAsString = dobAsString;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", dob=" + dob + ", dobAsString=" + dobAsString + ", address=" + address
				+ ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", createdDateAsString="
				+ createdDateAsString + ", modifiedDateAsString=" + modifiedDateAsString + ", department=" + department
				+ "]";
	}
	
	
	
	
}
