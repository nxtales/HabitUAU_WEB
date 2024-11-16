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
    public AmizadeDTO criarAmizade(String emailCliente1, String emailCliente2) {
        // Busca os clientes pelo email
        Cliente cliente1 = clienteRepository.findByEmail(emailCliente1)
                .orElseThrow(() -> new IllegalArgumentException("Cliente com email " + emailCliente1 + " não encontrado"));
        Cliente cliente2 = clienteRepository.findByEmail(emailCliente2)
                .orElseThrow(() -> new IllegalArgumentException("Cliente com email " + emailCliente2 + " não encontrado"));

        // Cria nova amizade e preenche manualmente todos os campos
        Amizade amizade = new Amizade();
        amizade.setCliente1(cliente1);  // Preenche cliente1 (id_cli1)
        amizade.setCliente2(cliente2);  // Preenche cliente2 (id_cli2)
        amizade.setDataAmizade(new Date()); // Define a data da amizade

        // Salva a amizade no banco
        Amizade novaAmizade = amizadesRepository.save(amizade);

        // Retorna um DTO preenchido com as informações relevantes dos clientes
        return new AmizadeDTO(
            novaAmizade.getCliente1().getCpf(),  // CPF do cliente1 (idcli1)
            novaAmizade.getCliente1().getNome(), // Nome do cliente1
            novaAmizade.getCliente2().getCpf(),  // CPF do cliente2 (idcli2)
            novaAmizade.getCliente2().getNome(), // Nome do cliente2
            novaAmizade.getDataAmizade()         // Data da amizade
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<AmizadeDTO> buscarAmizadesPorCliente(String cpf) {
        // Busca todas as amizades associadas ao CPF fornecido
        List<Amizade> amizades = amizadesRepository.findByClienteCpf(cpf);

        // Converte as amizades para DTOs
        return amizades.stream()
            .map(amizade -> new AmizadeDTO(
                amizade.getCliente1().getCpf(), // CPF do cliente1
                amizade.getCliente1().getNome(), // Nome do cliente1
                amizade.getCliente2().getCpf(), // CPF do cliente2
                amizade.getCliente2().getNome(), // Nome do cliente2
                amizade.getDataAmizade()        // Data da amizade
            ))
            .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public boolean deletarAmizade(String emailCliente1, String emailCliente2) {
        // Busca os clientes pelo email
        Cliente cliente1 = clienteRepository.findByEmail(emailCliente1)
                .orElseThrow(() -> new IllegalArgumentException("Cliente com email " + emailCliente1 + " não encontrado"));
        Cliente cliente2 = clienteRepository.findByEmail(emailCliente2)
                .orElseThrow(() -> new IllegalArgumentException("Cliente com email " + emailCliente2 + " não encontrado"));

        // Busca a amizade pelo cliente1 e cliente2
        Optional<Amizade> amizadeOpt = amizadesRepository.findByCliente1AndCliente2(cliente1, cliente2);

        if (amizadeOpt.isPresent()) {
            amizadesRepository.delete(amizadeOpt.get());
            return true;
        }

        return false;
    }
}
