package com.habituau.HabitUAU_WEB.model.repository;

import com.habituau.HabitUAU_WEB.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DesafioRepository extends JpaRepository<Desafio, Long> {

    // Buscar desafios por categoria ou parceiro
    List<Desafio> findByIdCategoriaOrIdParceiro(Long idCategoria, Long idParceiro);
}
