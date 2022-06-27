package com.daw.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@SpringBootApplication
@EnableJpaRepositories("com.daw.app.dao")
public class DawProjectApplication {

	@Value("${cloud_name}")
	private String cloudName;
	@Value("${api_key}")
	private String apiKey;
	@Value("${api_secret}")
	private String apiSecret;

	
	public static void main(String[] args) {
		SpringApplication.run(DawProjectApplication.class, args);
	}
	
	@Bean
	public Cloudinary cloudinary() {
		return new Cloudinary(ObjectUtils.asMap(
				  "cloud_name", cloudName,
				  "api_key", apiKey,
				  "api_secret", apiSecret));
	}
}
