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
import com.habituau.HabitUAU_WEB.model.entity.CategoriaDesafio;
import com.habituau.HabitUAU_WEB.model.entity.Cliente;
import com.habituau.HabitUAU_WEB.model.entity.Desafio;
import com.habituau.HabitUAU_WEB.model.entity.DesafioInscrito;
import com.habituau.HabitUAU_WEB.model.entity.DesafioTarefa;
import com.habituau.HabitUAU_WEB.model.entity.Parceiro;
import com.habituau.HabitUAU_WEB.model.repository.DesafioRepository;
import com.habituau.HabitUAU_WEB.model.repository.DesafioTarefasRepository;
import com.habituau.HabitUAU_WEB.model.repository.ParceiroRepository;
import com.habituau.HabitUAU_WEB.model.repository.CategoriasDesafiosRepository;
import com.habituau.HabitUAU_WEB.model.repository.ClienteRepository;
import com.habituau.HabitUAU_WEB.model.repository.DesafioInscritosRepository;
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
    
    @Autowired
    private CategoriasDesafiosRepository categoriasRepository;
    
    @Autowired
    private ParceiroRepository parceiroRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private DesafioInscritosRepository desafioInscritosRepository;

    // Método para obter tarefas do usuário inscrito no desafio
    @GetMapping("/getuserchallengetasks")
    public List<Map<String, Object>> getUserChallengeTasks(@RequestParam String cpf) {
        return desafioService.getUserChallengeTasks(cpf);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createChallenge(@RequestBody DesafioDTO desafioDTO) {
        // Verifique se o parceiro existe pelo ID fornecido
        Optional<Parceiro> parceiroOpt = parceiroRepository.findById(desafioDTO.getParceiroId());
        if (parceiroOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Parceiro com ID " + desafioDTO.getParceiroId() + " não encontrado.");
        }
        Parceiro parceiro = parceiroOpt.get();

        // Verifique se a categoria existe pelo ID fornecido
        Optional<CategoriaDesafio> categoriaOpt = categoriasRepository.findById(desafioDTO.getCategoriaId());
        if (categoriaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Categoria com ID " + desafioDTO.getCategoriaId() + " não encontrada.");
        }
        CategoriaDesafio categoria = categoriaOpt.get();

        // Crie o objeto Desafio com as referências pesquisadas para Parceiro e CategoriaDesafio
        Desafio desafio = new Desafio(
                parceiro,
                categoria,
                desafioDTO.getNome(),
                null
        );

        // Salva o Desafio para gerar o ID
        Desafio createdDesafio = desafioService.salvarDesafio(desafio);

        // Converte TarefaDTO para DesafioTarefa e associa cada uma ao Desafio criado
        List<DesafioTarefa> tarefas = desafioDTO.getTarefas().stream()
                .map(dto -> new DesafioTarefa(dto.getId(), dto.getNome(), dto.getqtde_pontos(), createdDesafio))
                .collect(Collectors.toList());

        // Salva cada tarefa no repositório
        for (DesafioTarefa tarefa : tarefas) {
            desafioTarefasRepository.save(tarefa);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(createdDesafio);
    }



    // Método para atualizar um desafio
    /*@PutMapping("/edit/{id}")
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

        // Mapeando para o DTO atualizado e verificando a completude de cada tarefa
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
        );
        return ResponseEntity.ok(updatedDesafio);
    }*/
    
    
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

    @PostMapping("/enroll")
    public ResponseEntity<?> enrollUserInChallenge(@RequestParam String cpfCliente, @RequestParam Long idDesafio) {
        // Verificar se o cliente existe
        Optional<Cliente> clienteOpt = clienteRepository.findByCPF(cpfCliente);
        if (clienteOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente com CPF " + cpfCliente + " não encontrado.");
        }
        Cliente cliente = clienteOpt.get();

        // Verificar se o desafio existe
        Optional<Desafio> desafioOpt = desafiorepository.findById(idDesafio);
        if (desafioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Desafio com ID " + idDesafio + " não encontrado.");
        }
        Desafio desafio = desafioOpt.get();

        // Verificar se o cliente já está inscrito no desafio
        Optional<DesafioInscrito> inscricaoExistente = desafioInscritosRepository.findByClienteCPFAndDesafioID(cpfCliente, idDesafio);
        if (inscricaoExistente.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Cliente já inscrito no desafio.");
        }

        // Criar a nova inscrição
        DesafioInscrito novaInscricao = new DesafioInscrito();
        novaInscricao.setCpfCliente(cpfCliente);
        novaInscricao.setIdDesafio(idDesafio);
        novaInscricao.setCliente(cliente);
        novaInscricao.setDesafio(desafio);

        // Salvar a inscrição
        desafioInscritosRepository.save(novaInscricao);

        return ResponseEntity.status(HttpStatus.CREATED).body("inscrito com sucesso!");
    }
}
