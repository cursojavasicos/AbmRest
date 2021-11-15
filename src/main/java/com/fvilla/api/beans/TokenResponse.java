package com.fvilla.api.beans;

import javax.ws.rs.core.Response.Status;

public class TokenResponse {

	private int statusCode;
	
	private String statusMessage;
	
	private String token;
	
	public TokenResponse() {
		this(Status.OK.getStatusCode(), Status.OK.getReasonPhrase());
	}
	
	public TokenResponse(int code, String message) {
		this(code, message, null);
	}
	
	public TokenResponse(int code, String message, String token) {
		this.setStatusCode(code);
		this.setStatusMessage(message);
		this.setToken(token);
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
