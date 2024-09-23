package com.habituau.HabitUAU_WEB.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habituau.HabitUAU_WEB.model.entity.Cliente;
import com.habituau.HabitUAU_WEB.model.entity.Desafio;
import com.habituau.HabitUAU_WEB.model.entity.DesafioInscritoTarefaCompleta;
import com.habituau.HabitUAU_WEB.model.entity.DesafioInscritosTarefasCompletasId;
import com.habituau.HabitUAU_WEB.model.repository.ClienteRepository;
import com.habituau.HabitUAU_WEB.model.repository.DesafioInscritosTarefasCompletasRepository;
import com.habituau.HabitUAU_WEB.model.repository.DesafioRepository;
import com.habituau.HabitUAU_WEB.service.GamificationService;

import java.util.Optional;

@Service
public class GamificationServiceImpl implements GamificationService {

    private final ClienteRepository clienteRepository;
    private final DesafioInscritosTarefasCompletasRepository tarefasCompletasRepository;
    private final DesafioRepository desafioRepository;

    @Autowired
    public GamificationServiceImpl(ClienteRepository clienteRepository,
                                    DesafioInscritosTarefasCompletasRepository tarefasCompletasRepository,
                                    DesafioRepository desafioRepository) {
        this.clienteRepository = clienteRepository;
        this.tarefasCompletasRepository = tarefasCompletasRepository;
        this.desafioRepository = desafioRepository;
    }

    @Override
    public void assignPoints(Long cpf, Long desafioId, int pontos) {
        Cliente cliente = clienteRepository.findByCpf(cpf.toString())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Desafio desafio = desafioRepository.findById(desafioId)
                .orElseThrow(() -> new RuntimeException("Desafio não encontrado"));

        DesafioInscritoTarefaCompleta tarefaCompleta = new DesafioInscritoTarefaCompleta();
        tarefaCompleta.setCliente(cliente);
        tarefaCompleta.setDesafio(desafio);
        tarefaCompleta.setSumPontos(pontos);

        tarefasCompletasRepository.save(tarefaCompleta);
    }

    @Override
    public int getUserPoints(Long cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf.toString())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        return tarefasCompletasRepository.findAllByCliente(cliente)
                .stream()
                .mapToInt(DesafioInscritoTarefaCompleta::getSumPontos)
                .sum();
    }

    @Override
    public int getChallengePoints(Long desafioId) {
        Desafio desafio = desafioRepository.findById(desafioId)
                .orElseThrow(() -> new RuntimeException("Desafio não encontrado"));

        return tarefasCompletasRepository.findAllByDesafio(desafio)
                .stream()
                .mapToInt(DesafioInscritoTarefaCompleta::getSumPontos)
                .sum();
    }
}
