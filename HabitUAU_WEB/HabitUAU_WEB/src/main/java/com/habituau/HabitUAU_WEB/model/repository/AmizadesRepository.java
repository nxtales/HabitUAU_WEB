package com.habituau.HabitUAU_WEB.model.repository;

import com.habituau.HabitUAU_WEB.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AmizadesRepository extends JpaRepository<Amizade, AmizadeId> {

    // Buscar amizades por um dos IDs de clientes
    List<Amizade> findByIdCl1OrIdCl2(String idCl1, String idCl2);
}