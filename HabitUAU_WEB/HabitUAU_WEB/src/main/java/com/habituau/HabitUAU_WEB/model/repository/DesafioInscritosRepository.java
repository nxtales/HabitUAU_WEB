package com.habituau.HabitUAU_WEB.model.repository;

import com.habituau.HabitUAU_WEB.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface DesafioInscritosRepository extends JpaRepository<DesafioInscrito, DesafioInscritosId> {

    // Buscar desafios inscritos por CPF do cliente e ID do desafio
    Optional<DesafioInscrito> findByClienteCPFAndDesafioID(String cpfCliente, Long idDesafio);
    
    List<DesafioInscrito> findByClienteCPF(String cpfCliente);
}
