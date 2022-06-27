/**
 * 
 */
package com.daw.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.daw.app.security.CustomAuthenticationFailureHandler;
import com.daw.app.security.CustomFilter;
import com.daw.app.security.CustomUserService;

/**
 * @author Orlando Pasaca
 *
 * @since 30 may. 2022
 */
@Configuration
@EnableWebSecurity
public class DawProjectSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomUserService customUserService;

	@Autowired
	CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	
	@Autowired
	CustomFilter customFilter;

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider());
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(customUserService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers("/css/**","/js/**", "/assets/**").permitAll()
		.antMatchers("/app/auth/**").permitAll()
		.anyRequest().authenticated()
		.and().formLogin()
		.loginPage("/app/auth/login")
		.loginProcessingUrl("/app/auth/login")
		.defaultSuccessUrl("/app/index")
		.failureHandler(customAuthenticationFailureHandler)
		.and().logout().logoutUrl("/app/auth/signout")
		.logoutSuccessUrl("/app/auth/login")
		.deleteCookies("JSESSIONID")
		.and()
		.addFilterBefore(customFilter, BasicAuthenticationFilter.class);
	}	
}
