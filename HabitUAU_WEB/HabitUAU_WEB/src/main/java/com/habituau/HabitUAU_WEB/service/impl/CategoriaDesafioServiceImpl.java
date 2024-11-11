package com.habituau.HabitUAU_WEB.service.impl;

import com.habituau.HabitUAU_WEB.model.entity.CategoriaDesafio;
import com.habituau.HabitUAU_WEB.model.repository.CategoriasDesafiosRepository;
import com.habituau.HabitUAU_WEB.service.CategoriaDesafioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaDesafioServiceImpl implements CategoriaDesafioService {

    @Autowired
    private CategoriasDesafiosRepository categoriaRepository;

    @Override
    @Transactional
    public CategoriaDesafio criarCategoria(CategoriaDesafio categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    @Transactional
    public void excluirCategoria(Long id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Categoria com ID " + id + " não encontrada.");
        }
    }

    @Override
    @Transactional
    public CategoriaDesafio alterarCategoria(Long id, CategoriaDesafio categoriaAtualizada) {
        Optional<CategoriaDesafio> categoriaOpt = categoriaRepository.findById(id);
        if (categoriaOpt.isPresent()) {
            CategoriaDesafio categoriaExistente = categoriaOpt.get();
            categoriaExistente.setNome(categoriaAtualizada.getNome());
            return categoriaRepository.save(categoriaExistente);
        } else {
            throw new IllegalArgumentException("Categoria com ID " + id + " não encontrada.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CategoriaDesafio> consultarCategoria(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaDesafio> listarCategorias() {
        return categoriaRepository.findAll();
    }
}
