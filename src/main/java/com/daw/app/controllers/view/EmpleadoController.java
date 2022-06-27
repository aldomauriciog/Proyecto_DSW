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
 * @since 5 jun. 2022
 */
@Controller
@RequestMapping("/app/empleados")
public class EmpleadoController {

	@GetMapping("/listado")
	public String listado() {
		return "empleados/listado";
	}
}
