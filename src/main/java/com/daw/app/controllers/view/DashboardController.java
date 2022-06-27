/**
 * 
 */
package com.daw.app.controllers.view;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Orlando Pasaca
 *
 * @since 29 may. 2022
 */
@Controller
@RequestMapping("/app")
public class DashboardController {

	@GetMapping({"/","","/index","/dashboard"})
	public String index(Model model) {
		
		return "index";
	}
}
