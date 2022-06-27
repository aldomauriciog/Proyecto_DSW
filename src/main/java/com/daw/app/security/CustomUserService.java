/**
 * 
 */
package com.daw.app.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.daw.app.dao.TbUsuarioDao;
import com.daw.app.entity.TbUsuario;
import com.daw.app.services.TbUsuarioService;

/**
 * @author Orlando Pasaca
 *
 * @since 3 jun. 2022
 */
@Service
public class CustomUserService implements UserDetailsService {

	@Autowired
	TbUsuarioDao usuarioDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<GrantedAuthority> roles = new ArrayList<>();
		TbUsuario usuario = usuarioDao.findByUsername(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario y/o contraseña invalidos");
		} else {
			UserDetails userDetails = new User(username, usuario.getClave(), roles);
			return userDetails;
		}
	}

}
