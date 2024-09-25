package com.habituau.HabitUAU_WEB.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habituau.HabitUAU_WEB.model.*;
import com.habituau.HabitUAU_WEB.model.entity.Cliente;
import com.habituau.HabitUAU_WEB.model.repository.ClienteRepository;
import com.habituau.HabitUAU_WEB.service.*;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	private final ClienteRepository clienteRepository;

	@Autowired
	public UserProfileServiceImpl(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@Override
	public void updateProfile(Long cpf, String preferencias, String metas) {
		Cliente cliente = clienteRepository.findById(cpf.toString())
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
		cliente.setPreferencias(preferencias);
		cliente.setMetas(metas);
		clienteRepository.save(cliente);
	}

	@Override
	public Cliente getProfile(Long cpf) {
		return clienteRepository.findById(cpf.toString())
				.orElseThrow(() -> new RuntimeException("Perfil não encontrado"));
	}

	@Override
	public String getUserHealthGoals(Long cpf) {
		Cliente cliente = clienteRepository.findById(cpf.toString())
				.orElseThrow(() -> new RuntimeException("Perfil não encontrado"));
		return cliente.getMetas();
	}
}
