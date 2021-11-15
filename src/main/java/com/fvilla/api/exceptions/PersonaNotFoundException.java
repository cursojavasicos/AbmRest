package com.fvilla.api.exceptions;

public class PersonaNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PersonaNotFoundException () {
		this("El id ingresado no corresponde a ninguna persona.");
	}
	
	public PersonaNotFoundException(String message) {
		super(message);
	}

}
