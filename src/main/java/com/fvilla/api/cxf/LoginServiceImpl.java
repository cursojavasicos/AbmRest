package com.fvilla.api.cxf;

import java.util.Date;
import java.util.UUID;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fvilla.api.beans.TokenResponse;
import com.fvilla.api.beans.UserInfo;
import com.fvilla.api.beans.response.ErrorResponse;
import com.fvilla.api.dao.UserDAO;
import com.fvilla.api.exceptions.DAOException;
import com.fvilla.api.util.Constants;
import com.fvilla.api.util.ErrorCode;

@Controller("login.service")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public Response getToken(UserInfo data) {

		UserInfo usr;

		try {
			usr = this.userDAO.getUserByName(data.getUser());

			if (data.getPassword().equals(usr.getPassword())) {
			
					Algorithm alg = Algorithm.HMAC256(Constants.SECRET);

					String token = JWT.create().withIssuer("Facundo").withSubject("API-Authentication")
							.withJWTId(UUID.randomUUID().toString())
							.withExpiresAt(new Date(System.currentTimeMillis() + 50000)) // En 50 segundos se vence
							.sign(alg);

					return Response.status(Status.OK)
							.entity(new TokenResponse(Status.OK.getStatusCode(), Status.OK.getReasonPhrase(), token))
							.build();
			
			} else {
				return Response.status(Status.UNAUTHORIZED).entity(new ErrorResponse(ErrorCode.PASSW_INCORRECTO))
						.build();
			}
		} catch (DAOException e) {
			return Response.status(Status.UNAUTHORIZED).entity(new ErrorResponse(Status.UNAUTHORIZED.getStatusCode(), Status.UNAUTHORIZED.getReasonPhrase()))
					.build();
		}

	}

}

