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
 * @since 4 jun. 2022
 */
@Controller
@RequestMapping("/app/cargos")
public class CargoController {
	
	@GetMapping(path = {"/index","/listado","/"})
	public String listado() {
		return "cargos/listado";
	}
}
