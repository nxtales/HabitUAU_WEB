package com.habituau.HabitUAU_WEB.api.resource;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.habituau.HabitUAU_WEB.api.dto.DesafioDTO;
import com.habituau.HabitUAU_WEB.api.dto.TarefaDTO;
import com.habituau.HabitUAU_WEB.model.entity.Desafio;
import com.habituau.HabitUAU_WEB.model.entity.DesafioTarefa;
import com.habituau.HabitUAU_WEB.model.repository.DesafioRepository;
import com.habituau.HabitUAU_WEB.model.repository.DesafioTarefasRepository;
import com.habituau.HabitUAU_WEB.model.repository.DesafioInscritosTarefasCompletasRepository;
import com.habituau.HabitUAU_WEB.service.ChallengeService;

@RestController
@RequestMapping("/api/challenges")
public class DesafioResource {

    @Autowired
    private ChallengeService desafioService;

    @Autowired
    private DesafioRepository desafiorepository;

    @Autowired
    private DesafioTarefasRepository desafioTarefasRepository;

    @Autowired
    private DesafioInscritosTarefasCompletasRepository tarefasCompletasRepository;

    // Método para obter tarefas do usuário inscrito no desafio
    @GetMapping("/getuserchallengetasks")
    public List<Map<String, Object>> getUserChallengeTasks(@RequestParam String cpf) {
        return desafioService.getUserChallengeTasks(cpf);
    }

    // Método para criar um novo desafio
    @PostMapping("/create")
    public ResponseEntity<Desafio> createChallenge(@RequestBody DesafioDTO desafioDTO) {
        // Converte TarefaDTO para DesafioTarefa, se necessário
        List<DesafioTarefa> tarefas = desafioDTO.getTarefas().stream()
                .map(dto -> new DesafioTarefa(dto.getId(), dto.getNome(), dto.isCompletada()))
                .collect(Collectors.toList());

        // Usa o construtor sem ID, pois é uma criação
        Desafio desafio = new Desafio(
                desafioDTO.getParceiroId(),
                desafioDTO.getCategoriaId(),
                desafioDTO.getNome(),
                tarefas
        );
        
        Desafio createdDesafio = desafioService.salvarDesafio(desafio);

        // Busca as tarefas associadas ao desafio criado
        //List<DesafioTarefa> tarefasCriadas = desafioTarefasRepository.findByDesafioID(createdDesafio.getId());

        /*// Mapeando para o DTO e verificando a completude de cada tarefa
        DesafioDTO createdDTO = new DesafioDTO(
                createdDesafio.getId(),
                createdDesafio.getParceiro().getId(),
                createdDesafio.getCategoria().getId(),
                createdDesafio.getNome(),
                tarefasCriadas.stream()
                       .map(t -> {
                           // Verifica se a tarefa está completa
                           boolean completada = tarefasCompletasRepository
                                   .findByClienteCPFAndTarefaIDAndDesafioID(cpf, t.getID(), createdDesafio.getId())
                                   .isPresent();
                           return new TarefaDTO(t.getID(), t.getNome_tarefa(), completada);
                       })
                       .collect(Collectors.toList())
        );*/
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDesafio);
    }

    // Método para atualizar um desafio
    @PutMapping("/edit/{id}")
    public ResponseEntity<Desafio> updateChallenge(@PathVariable Long id, @RequestBody DesafioDTO desafioDTO, @RequestParam String cpf) {
        // Converte TarefaDTO para DesafioTarefa, se necessário
        List<DesafioTarefa> tarefas = desafioDTO.getTarefas().stream()
                .map(dto -> new DesafioTarefa(dto.getId(), dto.getNome(), dto.isCompletada()))
                .collect(Collectors.toList());

        // Usa o construtor com ID para atualizações
        Desafio desafio = new Desafio(
                id,
                desafioDTO.getParceiroId(),
                desafioDTO.getCategoriaId(),
                desafioDTO.getNome(),
                tarefas
        );

        Desafio updatedDesafio = desafioService.atualizarDesafio(desafio);

        // Busca as tarefas associadas ao desafio atualizado
        List<DesafioTarefa> tarefasAtualizadas = desafioTarefasRepository.findByDesafioID(updatedDesafio.getId());

        /*// Mapeando para o DTO atualizado e verificando a completude de cada tarefa
        DesafioDTO updatedDTO = new DesafioDTO(
                updatedDesafio.getId(),
                updatedDesafio.getParceiro().getId(),
                updatedDesafio.getCategoria().getId(),
                updatedDesafio.getNome(),
                tarefasAtualizadas.stream()
                       .map(t -> {
                           boolean completada = tarefasCompletasRepository
                                   .findByClienteCPFAndTarefaIDAndDesafioID(cpf, t.getID(), updatedDesafio.getId())
                                   .isPresent();
                           return new TarefaDTO(t.getID(), t.getNome_tarefa(), completada);
                       })
                       .collect(Collectors.toList())
        );*/
        return ResponseEntity.ok(updatedDesafio);
    }
    
    
 // Método para deletar um desafio por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteChallenge(@PathVariable Long id) {
        Optional<Desafio> desafioOpt = desafiorepository.findById(id);
        if (desafioOpt.isPresent()) {
            desafiorepository.delete(desafioOpt.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
 // Método para buscar desafios por nome
    @GetMapping("/find")
    public ResponseEntity<List<Desafio>> findChallenges(@RequestParam String searchString) {
        List<Desafio> desafios = desafiorepository.findByNomeContaining(searchString);
        return ResponseEntity.ok(desafios);
    }

    // Outros métodos da classe permanecem inalterados...
}
