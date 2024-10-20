package com.habituau.HabitUAU_WEB.model.repository;

import com.habituau.HabitUAU_WEB.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DesafioTarefasRepository extends JpaRepository<DesafioTarefa, Long> {

    // Buscar tarefas por ID de desafio
    List<DesafioTarefa> findByDesafioID(Long idDesafio);
}
