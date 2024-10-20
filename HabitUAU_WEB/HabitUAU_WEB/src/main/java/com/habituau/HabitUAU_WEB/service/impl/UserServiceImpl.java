package com.habituau.HabitUAU_WEB.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.habituau.HabitUAU_WEB.exceptions.ErroAutenticacao;
import com.habituau.HabitUAU_WEB.exceptions.RegraNegocioException;
import com.habituau.HabitUAU_WEB.model.entity.*;
import com.habituau.HabitUAU_WEB.model.repository.ClienteRepository;
import com.habituau.HabitUAU_WEB.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public UserServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional
    public void registerUser(Cliente cliente) {
        if (!isValidEmailForRegistering(cliente.getEmail())) {
            throw new IllegalArgumentException("Formato de e-mail inválido");
        }
        if (!isValidCPF(Long.parseLong(cliente.getCpf()))) {
            throw new IllegalArgumentException("CPF inválido");
        }

        clienteRepository.save(cliente);
    }

    @Override
    public Cliente loginUser(String email, String senha) {
        // Verifica se o e-mail está no formato válido
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Formato de e-mail inválido");
        }

        // Busca o cliente pelo e-mail e senha, lançando uma exceção se não encontrar
        return clienteRepository.findByEmailAndSenha(email, senha)
                .orElseThrow(() -> new ErroAutenticacao("Credenciais inválidas"));
    }

    // Validação de e-mail com regex
    public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }
    
    public boolean isValidEmailForRegistering(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        boolean existe = clienteRepository.existsByEmail(email);
        	if(existe)
        	{
        		throw new RegraNegocioException("já existe um usuário cadastrado com este e-mail");
        	}
        return email.matches(emailRegex);
    }

    // Validação de CPF (somente números e quantidade correta de dígitos)
    private boolean isValidCPF(Long cpf) {
        String cpfString = String.valueOf(cpf);
        return cpfString.matches("\\d{11}");
    }

	public boolean canEnrollInChallenge(String cpf) {
		List<DesafioInscrito> desafiosAtivos = clienteRepository.findActiveChallengesByCPF(cpf);
		return desafiosAtivos.size() < 3;
	}

	@Override
	public Cliente registerUser(String email, String password, String nome, String sobrenome, Date dataNascimento,
			String genero, String cep, String cidade, String pais, String telefone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Cliente> findByEmail(String email) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void updateUserProfile(Long cpf, String nome, String sobrenome, String genero, String cidade, String pais,
			String telefone) {
		// TODO Auto-generated method stub
		
	}

}
