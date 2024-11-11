package com.habituau.HabitUAU_WEB.service;

import com.habituau.HabitUAU_WEB.model.entity.Parceiro;
import java.util.List;
import java.util.Optional;

public interface ParceiroService {
    Parceiro criarParceiro(Parceiro parceiro);
    void excluirParceiro(Long id);
    Parceiro alterarParceiro(Long id, Parceiro parceiroAtualizado);
    Optional<Parceiro> consultarParceiro(Long id);
    List<Parceiro> listarParceiros();
}

