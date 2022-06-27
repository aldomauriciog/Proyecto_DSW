/**
 * 
 */
package com.daw.app.controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Orlando Pasaca
 *
 * @since 29 may. 2022
 */
@Controller
public class PrincipalController {
	
	@GetMapping({"/","","/index"})
	public String home() {
		return "redirect:/app/index";
	}
}
