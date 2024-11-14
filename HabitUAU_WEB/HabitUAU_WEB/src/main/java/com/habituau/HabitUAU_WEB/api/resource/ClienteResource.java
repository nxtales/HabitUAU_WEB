package com.habituau.HabitUAU_WEB.api.resource;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.habituau.HabitUAU_WEB.api.dto.ClienteDTO;
import com.habituau.HabitUAU_WEB.exceptions.ErroAutenticacao;
import com.habituau.HabitUAU_WEB.exceptions.RegraNegocioException;
import com.habituau.HabitUAU_WEB.model.entity.Cliente;
import com.habituau.HabitUAU_WEB.model.repository.ClienteRepository;
import com.habituau.HabitUAU_WEB.service.UserService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteResource {
	
	@Autowired
	private ClienteRepository repository;

	private UserService service;

	public ClienteResource(UserService service) {
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity Create(@RequestBody ClienteDTO dto) {
		Cliente cliente = new Cliente(dto.getCPF(), dto.getEmail(), dto.getSenha(), dto.getNome(), dto.getSobrenome(),
				dto.getData_nascimento(), dto.getGenero(), dto.getCEP(), dto.getCidade(), dto.getPais(),
				dto.getTelefone());
		
		try {
			service.registerUser(cliente);
			return new ResponseEntity(cliente, HttpStatus.CREATED);
		} catch(RegraNegocioException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity Autenticate(@RequestBody ClienteDTO dto) {
		
		try {
			Cliente UsuarioAutenticado = service.loginUser(dto.getEmail(), dto.getSenha());
			return ResponseEntity.ok(UsuarioAutenticado);
		} catch(ErroAutenticacao e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	// Método para atualização de cliente
	@PutMapping("/update/{cpf}")
	public ResponseEntity<?> Update(@PathVariable("cpf") String cpf, @RequestBody ClienteDTO dto) {
		try {
			service.updateUserProfile(Long.valueOf(dto.getCPF()), dto.getNome(), dto.getSobrenome(), dto.getGenero(), dto.getCidade(), dto.getPais(), dto.getTelefone(), dto.getSenha());
			Optional<Cliente> clienteExistente = service.findByCPF(Long.valueOf(dto.getCPF()));
			return ResponseEntity.ok(clienteExistente);
		} catch (RegraNegocioException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	// Método para deletar cliente
	@DeleteMapping("/delete/{cpf}")
	public ResponseEntity<?> Delete(@PathVariable("cpf") String cpf) {
		try {
			service.deleteUserByCPF(Long.valueOf(cpf));
			return ResponseEntity.noContent().build();
		} catch (RegraNegocioException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping("/retrieve/{cpf}")
	public ResponseEntity<?> retrieveClientByCpf(@PathVariable String cpf) {
	    // Busca o cliente pelo CPF
	    Optional<Cliente> clienteOpt = repository.findByCPF(cpf);

	    // Verifica se o cliente foi encontrado
	    if (clienteOpt.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("Cliente com CPF " + cpf + " não encontrado.");
	    }

	    Cliente cliente = clienteOpt.get();

	    // Monta a resposta com as informações do cliente
	    Map<String, Object> clienteInfo = new HashMap<>();
	    clienteInfo.put("cpf", cliente.getCpf());
	    clienteInfo.put("nome", cliente.getNome());
	    clienteInfo.put("sobrenome", cliente.getSobrenome());
	    clienteInfo.put("email", cliente.getEmail());
	    clienteInfo.put("telefone", cliente.getTelefone());
	    clienteInfo.put("genero", cliente.getGenero());
	    clienteInfo.put("dataNascimento", cliente.getDataNascimento());
	    clienteInfo.put("endereco", Map.of(
	            "cep", cliente.getCep(),
	            "cidade", cliente.getCidade(),
	            "pais", cliente.getPais()
	    ));
	    clienteInfo.put("preferencias", cliente.getPreferencias());
	    clienteInfo.put("metas", cliente.getMetas());

	    return ResponseEntity.ok(clienteInfo);
	}
	
	// Método para obter todos os clientes
	@GetMapping("/getAll")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}
}
