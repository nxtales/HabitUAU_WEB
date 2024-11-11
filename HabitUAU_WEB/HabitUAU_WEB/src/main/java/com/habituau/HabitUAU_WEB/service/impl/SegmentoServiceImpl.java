package com.habituau.HabitUAU_WEB.service.impl;

import com.habituau.HabitUAU_WEB.model.entity.Segmento;
import com.habituau.HabitUAU_WEB.model.repository.SegmentosRepository;
import com.habituau.HabitUAU_WEB.service.SegmentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SegmentoServiceImpl implements SegmentoService {

    @Autowired
    private SegmentosRepository segmentoRepository;

    @Override
    @Transactional
    public Segmento criarSegmento(Segmento segmento) {
        return segmentoRepository.save(segmento);
    }

    @Override
    @Transactional
    public void excluirSegmento(Long id) {
        if (segmentoRepository.existsById(id)) {
            segmentoRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Segmento com ID " + id + " não encontrado.");
        }
    }

    @Override
    @Transactional
    public Segmento alterarSegmento(Long id, Segmento segmentoAtualizado) {
        Optional<Segmento> segmentoOpt = segmentoRepository.findById(id);
        if (segmentoOpt.isPresent()) {
            Segmento segmentoExistente = segmentoOpt.get();
            segmentoExistente.setNome(segmentoAtualizado.getNome());
            return segmentoRepository.save(segmentoExistente);
        } else {
            throw new IllegalArgumentException("Segmento com ID " + id + " não encontrado.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Segmento> consultarSegmento(Long id) {
        return segmentoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Segmento> listarSegmentos() {
        return segmentoRepository.findAll();
    }
}

