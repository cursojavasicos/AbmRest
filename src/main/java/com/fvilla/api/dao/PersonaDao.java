package com.fvilla.api.dao;

import java.util.List;

import com.fvilla.api.beans.Persona;
import com.fvilla.api.exceptions.DniDuplicadoException;
import com.fvilla.api.exceptions.PersonaNotFoundException;

public interface PersonaDao {
	
	List<Persona> getPersonas ();
	
	Persona getPersonaById (int id) throws PersonaNotFoundException;
	
	int savePersona (Persona persona) throws DniDuplicadoException;
	
	int deletePersonaById (int id) throws PersonaNotFoundException;
	
	int editPersona (Persona persona) throws PersonaNotFoundException, DniDuplicadoException;

}
