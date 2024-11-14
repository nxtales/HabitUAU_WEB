package com.habituau.HabitUAU_WEB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.habituau.HabitUAU_WEB.model.repository")
public class HabitUauWebApplication implements WebMvcConfigurer{

	/*@Override
	public void addCorsMappings(CorsRegistry registry) {
	    registry.addMapping("/**")
	        .allowedOrigins("https://ashy-ocean-0f803990f.5.azurestaticapps.net", "http://localhost:3000")
	        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
	        .allowedHeaders("*")
	        .allowCredentials(true)
	        .maxAge(3600);
	}*/
	
	public void addCorsMappings(CorsRegistry registry) {
	    registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(HabitUauWebApplication.class, args);
	}

}
