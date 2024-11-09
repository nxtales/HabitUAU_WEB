package com.habituau.HabitUAU_WEB.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.habituau.HabitUAU_WEB.model.entity.Cliente;
import com.habituau.HabitUAU_WEB.model.entity.Desafio;
import com.habituau.HabitUAU_WEB.model.entity.DesafioInscrito;
import com.habituau.HabitUAU_WEB.model.entity.DesafioTarefa;
import com.habituau.HabitUAU_WEB.model.repository.ClienteRepository;
import com.habituau.HabitUAU_WEB.model.repository.DesafioInscritosRepository;
import com.habituau.HabitUAU_WEB.model.repository.DesafioInscritosTarefasCompletasRepository;
import com.habituau.HabitUAU_WEB.model.repository.DesafioRepository;
import com.habituau.HabitUAU_WEB.model.repository.DesafioTarefasRepository;
import com.habituau.HabitUAU_WEB.service.ChallengeService;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    private final DesafioRepository desafioRepository;
    private final ClienteRepository clienteRepository;
    private final DesafioInscritosRepository inscritosrepository;
    private final DesafioTarefasRepository tarefasrepository;
    private final DesafioInscritosTarefasCompletasRepository tarefascompletasrepository;
    

    @Autowired
    public ChallengeServiceImpl(DesafioInscritosTarefasCompletasRepository tarefascompletasrepository, DesafioTarefasRepository tarefasrepository, DesafioRepository desafioRepository, ClienteRepository clienteRepository, DesafioInscritosRepository inscritosrepository) {
        this.desafioRepository = desafioRepository;
        this.clienteRepository = clienteRepository;
        this.inscritosrepository = inscritosrepository;
		this.tarefasrepository = tarefasrepository;
		this.tarefascompletasrepository = tarefascompletasrepository;
    }

    @Override
    public Desafio createChallenge(Long parceiroId, Long categoriaId, String nome, List<DesafioTarefa> tasks) {
        Desafio desafio = new Desafio(parceiroId, categoriaId, nome, tasks);
        return desafioRepository.save(desafio);
    }

    @Override
    public List<Desafio> suggestChallengesForUser(String searchInput) {
        //Cliente cliente = clienteRepository.findById(cpf.toString()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        //return desafioRepository.findByNomeContaining(cliente.getPreferencias(), cliente.getMetas());
    	
        // Buscar desafios usando a query personalizada
        return desafioRepository.findByNomeContaining(searchInput);
    }

    public List<Desafio> getUserChallenges(String cpf) {
        // Busca todos os desafios inscritos pelo CPF do cliente
        List<DesafioInscrito> desafiosInscritos = inscritosrepository.findByClienteCPF(cpf);
        
        // Extrai os IDs dos desafios dos desafios inscritos
        List<Long> desafioIds = desafiosInscritos.stream()
                .map(desafioInscrito -> desafioInscrito.getIdDesafio())
                .toList(); // Utiliza o método toList() disponível no Java 16 ou posterior
        
        // Busca os desafios com base nos IDs extraídos
        return desafioIds.isEmpty() ? List.of() : desafioRepository.findAllById(desafioIds);
    }

    @Override
    public boolean canUserEnrollInMoreChallenges(String cpf) {
        List<DesafioInscrito> desafiosAtivos = clienteRepository.findActiveChallengesByCPF(cpf);
        return desafiosAtivos.size() < 3;
    }

    @Transactional
	@Override
	public Desafio salvarDesafio(Desafio desafio) {
		return desafioRepository.save(desafio);
	}

	@Override
	public Desafio atualizarDesafio(Desafio desafio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletar(Desafio desafio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizarStatus(Desafio desafio) {
		// TODO Auto-generated method stub
		
	}
	
	 public List<Map<String, Object>> getUserChallengeTasks(String cpf) {
	        // Busca todos os desafios inscritos pelo CPF do cliente
	        List<DesafioInscrito> desafiosInscritos = inscritosrepository.findByClienteCPF(cpf);
	        
	        // Extrai os IDs dos desafios dos desafios inscritos
	        List<Long> desafioIds = desafiosInscritos.stream()
	                .map(DesafioInscrito::getIdDesafio)
	                .toList();

	        // Busca os desafios correspondentes
	        List<Desafio> desafios = desafioIds.isEmpty() ? List.of() : desafioRepository.findAllById(desafioIds);
	        
	        // Lista para armazenar os resultados
	        List<Map<String, Object>> result = new ArrayList<>();

	        // Para cada desafio, busca as tarefas e o status
	        for (Desafio desafio : desafios) {
	            Map<String, Object> desafioMap = new HashMap<>();
	            desafioMap.put("desafio", desafio);
	            List<Map<String, Object>> tasks = new ArrayList<>();

	            List<DesafioTarefa> tarefas = tarefasrepository.findByDesafioID(desafio.getId());
	            for (DesafioTarefa tarefa : tarefas) {
	                boolean isCompleted = !(tarefascompletasrepository.findByClienteCPFAndTarefaID(cpf, tarefa.getID()).isEmpty());
	                
	                Map<String, Object> taskMap = new HashMap<>();
	                taskMap.put("taskId", tarefa.getID());
	                taskMap.put("taskName", tarefa.getNome_tarefa());
	                taskMap.put("completed", isCompleted);
	                
	                tasks.add(taskMap);
	            }

	            desafioMap.put("tasks", tasks);
	            result.add(desafioMap);
	        }

	        return result;
	    }
    
    
}
