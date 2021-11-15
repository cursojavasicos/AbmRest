package com.fvilla.api.cxf;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fvilla.api.beans.Persona;
import com.fvilla.api.exceptions.DniDuplicadoException;
import com.fvilla.api.exceptions.PersonaNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Path("personas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface OperacionesAbm {

	@GET
	@Operation(description = "Devuelve lista de todas las personas", tags = "Operaciones ABM")
	public List<Persona> getPersonas();

	@GET
	@Operation(description = "Obtiene una persona segun el id ingresado", tags = "Operaciones ABM")
	@Path("/{id}")
	public Persona getPersona(@PathParam("id") int id) throws PersonaNotFoundException;

	@POST
	@Operation(description = "Guarda una nueva persona", 
			   tags = "Operaciones ABM",
			   security = @SecurityRequirement(name = "bearerAuth"))
	@Consumes(value = { MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_FORM_URLENCODED })
	public Response savePersona(@HeaderParam(HttpHeaders.AUTHORIZATION) String token,
			@FormParam("nombre") String nombre, @FormParam("apellido") String apellido, @FormParam("dni") int dni,
			@FormParam("fecha_nacimiento") Date fechaNacimiento, @FormParam("profesion") String profesion)
			throws DniDuplicadoException;

	@DELETE
	@Operation(description = "Elimina una persona segun el id ingresado", tags = "Operaciones ABM")
	@Path("/{id}")
	public Response deletePersonaById(@PathParam("id") int id) throws PersonaNotFoundException;

	@PUT
	@Operation(description = "Permite editar una persona", tags = "Operaciones ABM")
	@Path("/editar")
	public Response editPersona(Persona persona) throws PersonaNotFoundException, DniDuplicadoException;

}
