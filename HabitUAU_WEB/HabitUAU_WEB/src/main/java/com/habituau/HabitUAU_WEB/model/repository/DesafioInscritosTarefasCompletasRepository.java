package com.habituau.HabitUAU_WEB.model.repository;

import com.habituau.HabitUAU_WEB.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DesafioInscritosTarefasCompletasRepository extends JpaRepository<DesafioInscritoTarefaCompleta, DesafioInscritosTarefasCompletasId> {

    // Buscar tarefas completas por CPF do cliente, ID da tarefa e ID do desafio
    Optional<DesafioInscritoTarefaCompleta> findByClienteCPFAndTarefaIDAndDesafioID(String cpfCliente, Long idTarefa, Long idDesafio);
    
    List<DesafioInscritoTarefaCompleta> findAllByCliente(Cliente cliente);
    
    Optional<DesafioInscritoTarefaCompleta> findAllByDesafio(Desafio desafio);
    
}
