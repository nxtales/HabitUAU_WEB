package com.habituau.HabitUAU_WEB.api.resource;

import com.habituau.HabitUAU_WEB.api.dto.TarefaDTO;
import com.habituau.HabitUAU_WEB.model.entity.DesafioTarefa;
import com.habituau.HabitUAU_WEB.model.repository.DesafioTarefasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    // Endpoint para editar uma tarefa existente
    @PutMapping("/edit/{id}")
    public ResponseEntity<TarefaDTO> editTarefa(@PathVariable Long id, @RequestBody TarefaDTO tarefaDTO) {
        Optional<DesafioTarefa> tarefaOpt = tarefasRepository.findById(id);

        if (tarefaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        DesafioTarefa tarefa = tarefaOpt.get();
        tarefa.setNome_tarefa(tarefaDTO.getNome());
        tarefa.setqtde_pontos(tarefaDTO.getQtdepontos());

        // Salva as alterações
        DesafioTarefa updatedTarefa = tarefasRepository.save(tarefa);

        // Converte para DTO e retorna
        TarefaDTO updatedTarefaDTO = new TarefaDTO(
                updatedTarefa.getID(),
                updatedTarefa.getNome_tarefa(),
                updatedTarefa.getqtde_pontos(),
                false, // Completude permanece false
                updatedTarefa.getDesafio() != null ? updatedTarefa.getDesafio().getId() : null
        );

        return ResponseEntity.ok(updatedTarefaDTO);
    }

    // Endpoint para deletar uma tarefa
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTarefa(@PathVariable Long id) {
        Optional<DesafioTarefa> tarefaOpt = tarefasRepository.findById(id);

        if (tarefaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Deleta a tarefa
        tarefasRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
