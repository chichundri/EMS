package com.task.model;

import java.io.Serializable;
import java.util.Date;

public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String location;
	private Date creationDate;

	public Department() {
		super();
	}
	
	public Department(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
