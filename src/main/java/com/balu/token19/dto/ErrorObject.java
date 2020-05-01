package com.balu.token19.dto;

import lombok.Data;

@Data
public class ErrorObject {

	private String errorCode;

	private String errorMessage;
	
	public ErrorObject() {
		super();
	}

	public ErrorObject(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ErrorObject(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}
