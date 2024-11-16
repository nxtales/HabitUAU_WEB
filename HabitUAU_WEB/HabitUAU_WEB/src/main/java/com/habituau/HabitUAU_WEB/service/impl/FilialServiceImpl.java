package com.habituau.HabitUAU_WEB.service.impl;

import com.habituau.HabitUAU_WEB.exceptions.RegraNegocioException;
import com.habituau.HabitUAU_WEB.model.entity.Filial;
import com.habituau.HabitUAU_WEB.model.repository.FiliaisRepository;
import com.habituau.HabitUAU_WEB.service.FilialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilialServiceImpl implements FilialService {

    private final FiliaisRepository filialRepository;

    @Autowired
    public FilialServiceImpl(FiliaisRepository filialRepository) {
        this.filialRepository = filialRepository;
    }

    @Override
    public Filial createFilial(String cidade, String CEP, String endereco, String nome) {
        Filial filial = new Filial(cidade, CEP, endereco, nome);
        return filialRepository.save(filial);
    }

    @Override
    public void updateFilial(Long ID, String cidade, String CEP, String endereco, String nome) {
        Filial filial = filialRepository.findById(ID)
                .orElseThrow(() -> new RegraNegocioException("Filial com ID " + ID + " não encontrada."));

        if (cidade != null && !cidade.isEmpty()) filial.setCidade(cidade);
        if (CEP != null && !CEP.isEmpty()) filial.setCEP(CEP);
        if (endereco != null && !endereco.isEmpty()) filial.setEndereco(endereco);
        if (nome != null && !nome.isEmpty()) filial.setNome(nome);

        filialRepository.save(filial);
    }

    @Override
    public void deleteFilial(Long ID) {
        if (!filialRepository.existsById(ID)) {
            throw new RegraNegocioException("Filial com ID " + ID + " não encontrada.");
        }
        filialRepository.deleteById(ID);
    }

    @Override
    public Optional<Filial> findById(Long ID) {
        return filialRepository.findById(ID);
    }

    @Override
    public List<Filial> findAll() {
        return filialRepository.findAll();
    }
}
