package com.habituau.HabitUAU_WEB.model.repository;

import com.habituau.HabitUAU_WEB.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoriasDesafiosRepository extends JpaRepository<CategoriaDesafio, Long> {

    // Listar categorias de desafios
    List<CategoriaDesafio> findAll();
}
