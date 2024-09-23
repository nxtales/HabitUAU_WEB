package com.habituau.HabitUAU_WEB.model.repository;

import com.habituau.HabitUAU_WEB.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SegmentosRepository extends JpaRepository<Segmento, Long> {

    // Listar todos os segmentos
    List<Segmento> findAll();
}