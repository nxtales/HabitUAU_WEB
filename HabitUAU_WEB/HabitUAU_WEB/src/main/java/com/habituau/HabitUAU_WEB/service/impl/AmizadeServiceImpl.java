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

     // Cria nova amizade e preenche manualmente todos os campos, incluindo os IDs com CPF
        Amizade amizade = new Amizade();
        amizade.setCliente1(cliente1);           // Campo cliente1 (idcli1 no banco), associado ao cliente1
        amizade.setCliente2(cliente2);           // Campo cliente2 (idcli2 no banco), associado ao cliente2
        amizade.setDataAmizade(new Date());      // Define a data de amizade como a data atual

        // Salva a amizade no banco
        Amizade novaAmizade = amizadesRepository.save(amizade);

        // Retorna um DTO preenchido com as informações relevantes dos clientes
        return new AmizadeDTO(
            novaAmizade.getCliente1().getCpf(),     // Retorna CPF do cliente1 para o campo idcli1
            novaAmizade.getCliente1().getNome(),
            novaAmizade.getCliente2().getCpf(),     // Retorna CPF do cliente2 para o campo idcli2
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
