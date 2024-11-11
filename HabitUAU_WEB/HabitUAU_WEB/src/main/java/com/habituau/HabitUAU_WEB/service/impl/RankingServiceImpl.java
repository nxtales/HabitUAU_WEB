package com.habituau.HabitUAU_WEB.service.impl;

import com.habituau.HabitUAU_WEB.api.dto.RankingDTO;
import com.habituau.HabitUAU_WEB.model.entity.Cliente;
import com.habituau.HabitUAU_WEB.model.entity.Desafio;
import com.habituau.HabitUAU_WEB.model.entity.DesafioInscritoTarefaCompleta;
import com.habituau.HabitUAU_WEB.model.repository.ClienteRepository;
import com.habituau.HabitUAU_WEB.model.repository.DesafioInscritosTarefasCompletasRepository;
import com.habituau.HabitUAU_WEB.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RankingServiceImpl implements RankingService {

    @Autowired
    private DesafioInscritosTarefasCompletasRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Optional<RankingDTO> getMaiorPontuacaoPorDesafio(String cpfCliente) {
        // Busca o cliente pelo CPF
        Cliente cliente = clienteRepository.findByCPF(cpfCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente com CPF " + cpfCliente + " não encontrado"));

        // Busca todos os registros de tarefas completadas pelo cliente
        List<DesafioInscritoTarefaCompleta> completions = repository.findAllByCliente(cliente);

        if (completions.isEmpty()) {
            return Optional.empty();
        }

        // Agrupa os pontos por desafio e calcula o total para cada desafio
        Desafio desafioComMaiorPontuacao = completions.stream()
            .collect(Collectors.groupingBy(
                DesafioInscritoTarefaCompleta::getDesafio,
                Collectors.summingInt(DesafioInscritoTarefaCompleta::getSumPontos)
            ))
            .entrySet().stream()
            .max(Comparator.comparingInt(entry -> entry.getValue())) // Encontra o desafio com maior pontuação
            .map(entry -> entry.getKey())
            .orElseThrow();

        // Calcula a pontuação total do cliente no desafio de maior pontuação
        int pontosNoDesafio = completions.stream()
            .filter(completion -> completion.getDesafio().equals(desafioComMaiorPontuacao))
            .mapToInt(DesafioInscritoTarefaCompleta::getSumPontos)
            .sum();

        // Retorna o DTO com o desafio onde o cliente tem maior pontuação
        return Optional.of(new RankingDTO(
            cliente.getCpf(),
            cliente.getNome(),
            pontosNoDesafio,
            desafioComMaiorPontuacao.getId()
        ));
    }

    @Override
    public List<RankingDTO> getRankingGeral() {
        List<DesafioInscritoTarefaCompleta> completions = repository.findAll();

        return completions.stream()
            .collect(Collectors.groupingBy(
                DesafioInscritoTarefaCompleta::getCliente,
                Collectors.summingInt(DesafioInscritoTarefaCompleta::getSumPontos)
            ))
            .entrySet().stream()
            .map(entry -> new RankingDTO(
                entry.getKey().getCpf(),
                entry.getKey().getNome(),
                entry.getValue(),
                null
            ))
            .sorted((r1, r2) -> Integer.compare(r2.getPontos(), r1.getPontos()))
            .collect(Collectors.toList());
    }
}
