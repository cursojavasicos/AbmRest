package com.fvilla.api.providers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.fvilla.api.exceptions.PersonaNotFoundException;

public class PersonaNotFoundHandler implements ExceptionMapper<PersonaNotFoundException> {

	@Override
	public Response toResponse(PersonaNotFoundException ex) {
		return Response.status(404).entity(new ExceptionResponse("404", ex.getMessage())).type(MediaType.APPLICATION_JSON).build();
	}

}
