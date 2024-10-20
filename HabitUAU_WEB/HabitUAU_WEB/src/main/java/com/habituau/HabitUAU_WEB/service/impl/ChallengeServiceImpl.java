package com.habituau.HabitUAU_WEB.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habituau.HabitUAU_WEB.model.entity.Cliente;
import com.habituau.HabitUAU_WEB.model.entity.Desafio;
import com.habituau.HabitUAU_WEB.model.entity.DesafioInscrito;
import com.habituau.HabitUAU_WEB.model.entity.DesafioTarefa;
import com.habituau.HabitUAU_WEB.model.repository.ClienteRepository;
import com.habituau.HabitUAU_WEB.model.repository.DesafioInscritosRepository;
import com.habituau.HabitUAU_WEB.model.repository.DesafioRepository;
import com.habituau.HabitUAU_WEB.service.ChallengeService;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    private final DesafioRepository desafioRepository;
    private final ClienteRepository clienteRepository;
    private final DesafioInscritosRepository inscritosrepository;

    @Autowired
    public ChallengeServiceImpl(DesafioRepository desafioRepository, ClienteRepository clienteRepository, DesafioInscritosRepository inscritosrepository) {
        this.desafioRepository = desafioRepository;
        this.clienteRepository = clienteRepository;
        this.inscritosrepository = inscritosrepository;
    }

    @Override
    public Desafio createChallenge(Long parceiroId, Long categoriaId, String nome, List<DesafioTarefa> tasks) {
        Desafio desafio = new Desafio(parceiroId, categoriaId, nome, tasks);
        return desafioRepository.save(desafio);
    }

    @Override
    public List<Desafio> suggestChallengesForUser(String searchInput) {
        //Cliente cliente = clienteRepository.findById(cpf.toString()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        //return desafioRepository.findByNomeContaining(cliente.getPreferencias(), cliente.getMetas());
    	
        // Buscar desafios usando a query personalizada
        return desafioRepository.findByNomeContaining(searchInput);
    }

    public List<Desafio> getUserChallenges(String cpf) {
        // Busca todos os desafios inscritos pelo CPF do cliente
        List<DesafioInscrito> desafiosInscritos = inscritosrepository.findByClienteCPF(cpf);
        
        // Extrai os IDs dos desafios dos desafios inscritos
        List<Long> desafioIds = desafiosInscritos.stream()
                .map(desafioInscrito -> desafioInscrito.getIdDesafio())
                .toList(); // Utiliza o método toList() disponível no Java 16 ou posterior
        
        // Busca os desafios com base nos IDs extraídos
        return desafioIds.isEmpty() ? List.of() : desafioRepository.findAllById(desafioIds);
    }

    @Override
    public boolean canUserEnrollInMoreChallenges(String cpf) {
        List<DesafioInscrito> desafiosAtivos = clienteRepository.findActiveChallengesByCPF(cpf);
        return desafiosAtivos.size() < 3;
    }
}
