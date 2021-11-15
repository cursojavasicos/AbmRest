package com.fvilla.api.exceptions;

public class DniDuplicadoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DniDuplicadoException () {
		this("El dni ingresado ya esta en uso");
	}
	
	public DniDuplicadoException(String message) {
		super(message);
	}

}
