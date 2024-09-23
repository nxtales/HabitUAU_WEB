package com.habituau.HabitUAU_WEB.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habituau.HabitUAU_WEB.model.entity.Cliente;
import com.habituau.HabitUAU_WEB.model.entity.Desafio;
import com.habituau.HabitUAU_WEB.model.entity.DesafioInscrito;
import com.habituau.HabitUAU_WEB.model.entity.DesafioTarefa;
import com.habituau.HabitUAU_WEB.model.repository.ClienteRepository;
import com.habituau.HabitUAU_WEB.model.repository.DesafioRepository;
import com.habituau.HabitUAU_WEB.service.ChallengeService;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    private final DesafioRepository desafioRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public ChallengeServiceImpl(DesafioRepository desafioRepository, ClienteRepository clienteRepository) {
        this.desafioRepository = desafioRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Desafio createChallenge(Long parceiroId, Long categoriaId, String nome, List<DesafioTarefa> tasks) {
        Desafio desafio = new Desafio(parceiroId, categoriaId, nome, tasks);
        return desafioRepository.save(desafio);
    }

    @Override
    public List<Desafio> suggestChallengesForUser(Long cpf) {
        Cliente cliente = clienteRepository.findById(cpf.toString()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return desafioRepository.findSuggestedChallenges(cliente.getPreferencias(), cliente.getMetas());
    }

    @Override
    public List<Desafio> getUserChallenges(Long cpf) {
        return desafioRepository.findChallengesByClientId(cpf);
    }

    @Override
    public boolean canUserEnrollInMoreChallenges(Long cpf) {
        List<DesafioInscrito> desafiosAtivos = clienteRepository.findActiveChallengesByClientId(cpf);
        return desafiosAtivos.size() < 3;
    }
}
