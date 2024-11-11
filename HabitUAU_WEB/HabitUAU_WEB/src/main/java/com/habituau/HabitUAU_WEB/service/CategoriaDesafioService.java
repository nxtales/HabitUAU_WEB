package com.habituau.HabitUAU_WEB.service;

import com.habituau.HabitUAU_WEB.model.entity.CategoriaDesafio;
import java.util.List;
import java.util.Optional;

public interface CategoriaDesafioService {
    CategoriaDesafio criarCategoria(CategoriaDesafio categoria);
    void excluirCategoria(Long id);
    CategoriaDesafio alterarCategoria(Long id, CategoriaDesafio categoriaAtualizada);
    Optional<CategoriaDesafio> consultarCategoria(Long id);
    List<CategoriaDesafio> listarCategorias();
}

