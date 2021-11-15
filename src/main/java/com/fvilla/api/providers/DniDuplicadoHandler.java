package com.fvilla.api.providers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.fvilla.api.exceptions.DniDuplicadoException;

public class DniDuplicadoHandler implements ExceptionMapper<DniDuplicadoException> {

	@Override
	public Response toResponse(DniDuplicadoException ex) {
		return Response.status(400).entity(new ExceptionResponse("400", ex.getMessage())).type(MediaType.APPLICATION_JSON).build();
	}

}
