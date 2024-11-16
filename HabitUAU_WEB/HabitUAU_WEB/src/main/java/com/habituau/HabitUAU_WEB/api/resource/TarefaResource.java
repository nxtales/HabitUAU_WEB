package com.habituau.HabitUAU_WEB.api.resource;

import com.habituau.HabitUAU_WEB.api.dto.TarefaDTO;
import com.habituau.HabitUAU_WEB.model.entity.DesafioTarefa;
import com.habituau.HabitUAU_WEB.model.repository.DesafioTarefasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaResource {

    @Autowired
    private DesafioTarefasRepository tarefasRepository;

    @GetMapping("/all")
    public ResponseEntity<List<TarefaDTO>> getAllTarefas() {
        // Busca todas as tarefas da base de dados
        List<DesafioTarefa> tarefas = tarefasRepository.findAll();

        // Converte as tarefas para o DTO
        List<TarefaDTO> tarefasDTO = tarefas.stream()
                .map(tarefa -> new TarefaDTO(
                        tarefa.getID(),
                        tarefa.getNome_tarefa(),
                        tarefa.getqtde_pontos(),
                        false, // Preenche como não completada, já que não há contexto de completude aqui
                        tarefa.getDesafio() != null ? tarefa.getDesafio().getId() : null // Inclui o ID do desafio
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(tarefasDTO);
    }
}
