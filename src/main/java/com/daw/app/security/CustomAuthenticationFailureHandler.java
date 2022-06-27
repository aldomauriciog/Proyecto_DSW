/**
 * 
 */
package com.daw.app.security;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Orlando Pasaca
 *
 * @since 3 jun. 2022
 */
@Service
public class CustomAuthenticationFailureHandler 
  implements AuthenticationFailureHandler {
 
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
	     request.setAttribute("message", "Usuario y/o contraseña invalidos");
	     response.addHeader("message", "Usuario y/o contraseña invalidos");
	     request.getServletContext().getRequestDispatcher("/app/auth/login").forward(request, response);
	}

  
}
