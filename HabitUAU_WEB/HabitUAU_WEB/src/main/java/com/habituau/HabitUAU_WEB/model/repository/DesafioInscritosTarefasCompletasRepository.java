package com.habituau.HabitUAU_WEB.model.repository;

import com.habituau.HabitUAU_WEB.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DesafioInscritosTarefasCompletasRepository extends JpaRepository<DesafioInscritoTarefaCompleta, DesafioInscritosTarefasCompletasId> {

    // Buscar tarefas completas por CPF do cliente, ID da tarefa e ID do desafio
    Optional<DesafioInscritoTarefaCompleta> findByCpfClienteAndIdTarefaAndIdDesafio(String cpfCliente, Long idTarefa, Long idDesafio);
}
