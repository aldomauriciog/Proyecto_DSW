/**
 * 
 */
package com.daw.app.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.daw.app.dao.TbUsuarioDao;
import com.daw.app.entity.TbUsuario;
import com.daw.app.services.TbUsuarioService;

/**
 * @author Orlando Pasaca
 *
 * @since 3 jun. 2022
 */
@Component
public class CustomFilter extends GenericFilterBean {

	@Autowired
	TbUsuarioDao usuarioDao;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String path = ((HttpServletRequest) request).getServletPath();
		if (SecurityContextHolder.getContext().getAuthentication() != null && path.contains("/app") && !path.contains("/app/auth")) {
			User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			TbUsuario usuario = usuarioDao.findByUsername(principal.getUsername());
			request.setAttribute("usuario",
					usuario.getTbEmpleado().getNombres().concat(" ").concat(usuario.getTbEmpleado().getApePaterno())
							.concat(" ").concat(usuario.getTbEmpleado().getApeMaterno()));
			request.setAttribute("foto", usuario.getFoto());
		}
		chain.doFilter(request, response);
	}

}
