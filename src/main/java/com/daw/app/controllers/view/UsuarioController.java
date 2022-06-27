/**
 * 
 */
package com.daw.app.controllers.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.daw.app.services.TbUsuarioService;

/**
 * @author Orlando Pasaca
 *
 * @since 30 may. 2022
 */
@Controller
@RequestMapping("/app/usuarios")
public class UsuarioController {
	
	@Autowired
	TbUsuarioService usuarioService;
	
	@GetMapping("/listado")
	public String listado(Model model){
		return "usuarios/listado";
	}
}
