package com.habituau.HabitUAU_WEB.model.repository;

import com.habituau.HabitUAU_WEB.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FiliaisRepository extends JpaRepository<Filial, Long> {

    // Listar todas as filiais
    List<Filial> findAll();
}