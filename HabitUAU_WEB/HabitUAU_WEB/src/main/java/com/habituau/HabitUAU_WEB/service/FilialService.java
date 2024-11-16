package com.habituau.HabitUAU_WEB.service;

import com.habituau.HabitUAU_WEB.model.entity.Filial;

import java.util.List;
import java.util.Optional;

public interface FilialService {
    Filial createFilial(String cidade, String CEP, String endereco, String nome);
    void updateFilial(Long ID, String cidade, String CEP, String endereco, String nome);
    void deleteFilial(Long ID);
    Optional<Filial> findById(Long ID);
    List<Filial> findAll();
}
