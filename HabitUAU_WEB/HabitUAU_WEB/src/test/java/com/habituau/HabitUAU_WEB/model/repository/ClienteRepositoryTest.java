package com.habituau.HabitUAU_WEB.model.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.junit4.SpringRunner; //JUnit é a biblioteca usada p/ os testes, isso já estava no doc do noriega

import com.habituau.HabitUAU_WEB.model.entity.Cliente;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Adicione esta linha
//@EnableJpaRepositories(basePackages = "com.habituau.HabitUAU_WEB.model.repository")
@ExtendWith(SpringExtension.class)
public class ClienteRepositoryTest {

	//essa é a classe de teste de integração do ClienteRepository!
	//diferente do teste unitário, essa classe acessa a base de dados
	//por meio do spring data jpa
	//(essas anotações vão ajudar a nat do futuro)
	
	@Autowired
	ClienteRepository repository;
	
	@Rollback
	@Test
	public void deveVerificarAExistenciaDeUmEmail() {
		//TODO METODO DE TESTE VAI SER VOID
		//cenário
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
		}
        
		@Rollback
		@Test
		public void deveRetornarFalsoSeOEmailNaoExistir() {
			//TODO METODO DE TESTE VAI SER VOID
			//cenário
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
		        
		        if(repository.existsByEmail("cliente@example.com") != false) {
		        	repository.delete(clienteTeste);
		        }
		        
				//ação/execução do teste em si
				boolean result = repository.existsByEmail("cliente@example.com");
				
				//verificação
				Assertions.assertThat(result).isFalse();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
		
		@Test
		public void devePersistirUmUsuarioNaBaseDeDados() {
			//cenário
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
		        
		        //acao 
		        Cliente clientesalvo = repository.save(clienteTeste);
				
				//verificação
				Assertions.assertThat(clientesalvo.getCpf()).isNotNull();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Test
		public void deveBuscarUmUsuarioPorEmail() {
			//cenário
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
		        
		        //acao 
		        Cliente clientesalvo = repository.save(clienteTeste);
				
				//verificação
				Optional<Cliente> result = repository.findByEmailAndSenha("cliente@example.com", "senhaSegura123");
				Assertions.assertThat(result.isPresent());
		        
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Test
		public void deveRetornarVazioAoBuscarUmUsuarioPorEmailQuandoNaoiExistirNaBase() {
			//cenário
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
		        
		        //acao 
		        Cliente clientesalvo = repository.save(clienteTeste);
				
				//verificação
				Optional<Cliente> result = repository.findByEmailAndSenha("natalia@natalianatalia.com", "senhaSegura123");
				Assertions.assertThat(result.isPresent()).isFalse();
		        
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
