package com.habituau.HabitUAU_WEB.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.habituau.HabitUAU_WEB.exceptions.RegraNegocioException;
import com.habituau.HabitUAU_WEB.model.entity.Cliente;
import com.habituau.HabitUAU_WEB.model.repository.ClienteRepository;
import com.habituau.HabitUAU_WEB.service.impl.UserServiceImpl;

@SpringBootTest //carrega contexto de teste do spring boot
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

	@Autowired
	UserServiceImpl service;
	
	@Autowired
	ClienteRepository repository;
	
	@Test
	public void deveValidarEmail() {
		//cenario
		
		//acao
		service.isValidEmail("natalia@nataliantalaia.com"); //IMPLEMENTAR NAO DEU TEMPO
		
		// Verificação
        Assertions.assertThat(service.isValidEmail("natalia@nataliantalaia.com")).isTrue();
	}
	
	@Test
	public void deveLancarErroQuandoExistirEmailCadastrado( ) {
		//cenario
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNascimento;
		try {
			dataNascimento = sdf.parse("15/09/1990");
			
			// Criar um novo cliente
	        Cliente clienteTeste = new Cliente(
	        	"12345678900",           // CPF
	            "cliente@example.com",    // Email
	            "senhaSegura123",         // Senha
	            "João",                   // Nome
	            "Silva",                  // Sobrenome
	            dataNascimento,           // Data de Nascimento
	            "Masculino",              // Gênero
	            "12345-678",              // CEP
	            "São Paulo",              // Cidade
	            "Brasil",                 // País
	            "+55 11 98765-4321"       // Telefone
	        );
	        
	        repository.save(clienteTeste);
	      //ação/execução do teste em si
			boolean result = repository.existsByEmail("cliente@example.com");
			
			//verificação
			Assertions.assertThat(result).isTrue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); }	
		
        // Ação e verificação da exceção
        Assertions.assertThatThrownBy(() -> service.isValidEmail("cliente@example.com"))
            .isInstanceOf(RegraNegocioException.class);
	}
	
}
