package com.habituau.HabitUAU_WEB.model.repository;

import com.habituau.HabitUAU_WEB.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ParceiroRepository extends JpaRepository<Parceiro, Long> {

    // Listar todos os parceiros e seus cupons
    List<Parceiro> findAll();
}
