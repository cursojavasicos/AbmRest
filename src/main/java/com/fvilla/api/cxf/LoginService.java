package com.fvilla.api.cxf;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fvilla.api.beans.UserInfo;

import io.swagger.v3.oas.annotations.Operation;


@Path("login")
@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public interface LoginService  {

	@POST
	@Operation(description = "Obtener un token JWT",
			   operationId = "login",
			   tags = "Token")
	@Path("/")
	Response getToken(UserInfo user);

}

