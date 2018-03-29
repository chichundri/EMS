package com.task.util;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class Response<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public T data;
	
	public Response(T data) {
		super();
		this.data = data;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	public static void main(String[] args) {
	}





	
}
