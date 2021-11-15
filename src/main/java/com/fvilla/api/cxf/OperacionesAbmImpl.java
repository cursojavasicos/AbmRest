package com.fvilla.api.cxf;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fvilla.api.beans.Persona;
import com.fvilla.api.beans.response.ErrorResponse;
import com.fvilla.api.dao.PersonaDao;
import com.fvilla.api.exceptions.DniDuplicadoException;
import com.fvilla.api.exceptions.PersonaNotFoundException;
import com.fvilla.api.util.Constants;

@Controller("OperacionesAbm")
public class OperacionesAbmImpl implements OperacionesAbm{

	@Autowired
	private PersonaDao personaDao;
	
	@Override
	public List<Persona> getPersonas(){
		return personaDao.getPersonas();
	}

	@Override
	public Persona getPersona(int id) throws PersonaNotFoundException {
		return personaDao.getPersonaById(id);
	}

	@Override
	public Response savePersona(String token, String nombre, String apellido, int dni, Date fechaNacimiento,
			String profesion) throws DniDuplicadoException {

		if (token != null && token.toLowerCase().startsWith("bearer")) {

			String subToken = token.substring(7);

			Algorithm alg = Algorithm.HMAC256(Constants.SECRET);

			JWTVerifier verifier = JWT.require(alg).withIssuer("Facundo").withSubject("API-Authentication").build();
			try {
				DecodedJWT jwt = verifier.verify(subToken);

				jwt.getClaims().entrySet().forEach(System.out::println);

				Persona persona = new Persona(nombre, apellido, dni, fechaNacimiento, profesion);
				personaDao.savePersona(persona);
			
				return Response.status(Status.OK).entity("Persona agregada correctamente!").build();
			
			} catch (JWTVerificationException e) {
				return Response.status(Status.BAD_REQUEST).entity(new ErrorResponse(Status.BAD_REQUEST.getStatusCode(), e.getMessage())).build();
			}
		} else {
			return Response.status(Status.UNAUTHORIZED).entity(new ErrorResponse(Status.UNAUTHORIZED.getStatusCode(), Status.UNAUTHORIZED.getReasonPhrase())).build();
		}
		
	}

	@Override
	public Response deletePersonaById( int id) throws PersonaNotFoundException {
		personaDao.deletePersonaById(id);
		return Response.status(Status.OK).entity("Persona eliminada correctamente!").build();
	}

	@Override
	public Response editPersona(Persona persona) throws PersonaNotFoundException, DniDuplicadoException {
		personaDao.editPersona(persona);
		return Response.status(Status.OK).entity("Persona editada correctamente!").build();
	}
	
}
