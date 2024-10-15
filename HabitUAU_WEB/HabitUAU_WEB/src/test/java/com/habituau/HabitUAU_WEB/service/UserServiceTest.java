package com.habituau.HabitUAU_WEB.service;

import org.junit.Test;
import org.junit.Test.None;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest //carrega contexto de teste do spring boot
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

	@Autowired
	UserService service;
	
	@Test(expected = Test.None.class)
	public void deveValidarEmail() {
		//cenario
		
		//acao
		//service.ValidarEmail("natalia@nataliantalaia.com") //IMPLEMENTAR NAO DEU TEMPO
	}
	
}
