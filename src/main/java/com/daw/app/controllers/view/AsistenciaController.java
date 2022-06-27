/**
 * 
 */
package com.daw.app.controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Orlando Pasaca
 *
 * @since 30 may. 2022
 */
@Controller
@RequestMapping("/app/asistencia")
public class AsistenciaController {
	
	@GetMapping("/index")
	public String index() {
		return "asistencia/index";
	}
}
