package com.habituau.HabitUAU_WEB.service;

import com.habituau.HabitUAU_WEB.model.entity.Segmento;
import java.util.List;
import java.util.Optional;

public interface SegmentoService {
    Segmento criarSegmento(Segmento segmento);
    void excluirSegmento(Long id);
    Segmento alterarSegmento(Long id, Segmento segmentoAtualizado);
    Optional<Segmento> consultarSegmento(Long id);
    List<Segmento> listarSegmentos();
}

