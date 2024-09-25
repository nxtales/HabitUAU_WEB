package com.habituau.HabitUAU_WEB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.habituau.HabitUAU_WEB.model.repository")
public class HabitUauWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(HabitUauWebApplication.class, args);
	}

}
