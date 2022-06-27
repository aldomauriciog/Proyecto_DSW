/**
 * 
 */
package com.daw.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * @author Orlando Pasaca
 *
 * @since 3 jun. 2022
 */
@Configuration
@PropertySources({
	@PropertySource("classpath:env.properties")
})
public class EnvPropertiesConfig {
	
}
