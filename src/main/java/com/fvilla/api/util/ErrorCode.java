package com.fvilla.api.util;

public enum ErrorCode {

	PASSW_INCORRECTO(1, "Password incorrecto");
	
	private int code;
	
	private String description;
	
	private ErrorCode(int code, String desc) {
		this.setCode(code);
		this.setDescription(desc);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
