package com.habituau.HabitUAU_WEB.service.impl;

import com.habituau.HabitUAU_WEB.api.dto.AmizadeDTO;
import com.habituau.HabitUAU_WEB.model.entity.Amizade;
import com.habituau.HabitUAU_WEB.model.entity.Cliente;
import com.habituau.HabitUAU_WEB.model.repository.AmizadesRepository;
import com.habituau.HabitUAU_WEB.model.repository.ClienteRepository;
import com.habituau.HabitUAU_WEB.service.AmizadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AmizadeServiceImpl implements AmizadeService {

    @Autowired
    private AmizadesRepository amizadesRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    @Transactional
    public AmizadeDTO criarAmizade(String cpfCliente1, String cpfCliente2) {
        // Busca os clientes pelo CPF
        Cliente cliente1 = clienteRepository.findByCPF(cpfCliente1)
                .orElseThrow(() -> new IllegalArgumentException("Cliente com CPF " + cpfCliente1 + " não encontrado"));
        Cliente cliente2 = clienteRepository.findByCPF(cpfCliente2)
                .orElseThrow(() -> new IllegalArgumentException("Cliente com CPF " + cpfCliente2 + " não encontrado"));

        // Cria nova amizade
        Amizade amizade = new Amizade();
        amizade.setCliente1(cliente1);
        amizade.setCliente2(cliente2);
        amizade.setDataAmizade(new Date());

        Amizade novaAmizade = amizadesRepository.save(amizade);

        return new AmizadeDTO(
            novaAmizade.getCliente1().getCpf(),
            novaAmizade.getCliente1().getNome(),
            novaAmizade.getCliente2().getCpf(),
            novaAmizade.getCliente2().getNome(),
            novaAmizade.getDataAmizade()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<AmizadeDTO> buscarAmizadesPorCliente(String cpf) {
        List<Amizade> amizades = amizadesRepository.findByClienteCpf(cpf);

        return amizades.stream()
            .map(amizade -> new AmizadeDTO(
                amizade.getCliente1().getCpf(),
                amizade.getCliente1().getNome(),
                amizade.getCliente2().getCpf(),
                amizade.getCliente2().getNome(),
                amizade.getDataAmizade()
            ))
            .collect(Collectors.toList());
    }
}
