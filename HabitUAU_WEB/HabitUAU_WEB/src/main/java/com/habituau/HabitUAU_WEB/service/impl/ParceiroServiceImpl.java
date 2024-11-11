package com.habituau.HabitUAU_WEB.service.impl;

import com.habituau.HabitUAU_WEB.model.entity.Parceiro;
import com.habituau.HabitUAU_WEB.model.repository.ParceiroRepository;
import com.habituau.HabitUAU_WEB.service.ParceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ParceiroServiceImpl implements ParceiroService {

    @Autowired
    private ParceiroRepository parceiroRepository;

    @Override
    @Transactional
    public Parceiro criarParceiro(Parceiro parceiro) {
        return parceiroRepository.save(parceiro);
    }

    @Override
    @Transactional
    public void excluirParceiro(Long id) {
        if (parceiroRepository.existsById(id)) {
            parceiroRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Parceiro com ID " + id + " não encontrado.");
        }
    }

    @Override
    @Transactional
    public Parceiro alterarParceiro(Long id, Parceiro parceiroAtualizado) {
        Optional<Parceiro> parceiroOpt = parceiroRepository.findById(id);
        if (parceiroOpt.isPresent()) {
            Parceiro parceiroExistente = parceiroOpt.get();
            parceiroExistente.setSegmento(parceiroAtualizado.getSegmento());
            parceiroExistente.setqtde_desafios(parceiroAtualizado.getqtde_desafios());
            parceiroExistente.setFoto(parceiroAtualizado.getFoto());
            return parceiroRepository.save(parceiroExistente);
        } else {
            throw new IllegalArgumentException("Parceiro com ID " + id + " não encontrado.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Parceiro> consultarParceiro(Long id) {
        return parceiroRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Parceiro> listarParceiros() {
        return parceiroRepository.findAll();
    }
}

