package com.task.model;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Student {

	private int id;
	private String name;
	
	public Student() {
		System.out.println("Student constructor");
		System.out.println(this.hashCode());
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
	
	public static void main(String[] args) {
		ApplicationContext context =
			    new ClassPathXmlApplicationContext("/resources/applicationContext.xml");
//		
	}

}
