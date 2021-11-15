package com.fvilla.api.providers;

public class ExceptionResponse {

	private String httpCode;

	private String message;

	public ExceptionResponse() {}

	public ExceptionResponse(String httpCode, String message) {
		super();
		this.httpCode = httpCode;
		this.message = message;
	}

	public String getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(String httpCode) {
		this.httpCode = httpCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	

}
