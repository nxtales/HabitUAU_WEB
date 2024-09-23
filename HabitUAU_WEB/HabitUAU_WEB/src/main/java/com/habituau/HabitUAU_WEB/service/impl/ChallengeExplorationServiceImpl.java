package com.habituau.HabitUAU_WEB.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habituau.HabitUAU_WEB.model.entity.Desafio;
import com.habituau.HabitUAU_WEB.model.entity.DesafioTarefa;
import com.habituau.HabitUAU_WEB.model.repository.DesafioRepository;
import com.habituau.HabitUAU_WEB.service.ChallengeService;

@Service
public class ChallengeExplorationServiceImpl implements ChallengeService {

    private final DesafioRepository desafioRepository;

    @Autowired
    public ChallengeExplorationServiceImpl(DesafioRepository desafioRepository) {
        this.desafioRepository = desafioRepository;
    }

    //@Override
    public List<Desafio> exploreAvailableChallenges() {
        return desafioRepository.findAll();
    }

	@Override
	public Desafio createChallenge(Long parceiroId, Long categoriaId, String nome, List<DesafioTarefa> tasks) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Desafio> suggestChallengesForUser(Long cpf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Desafio> getUserChallenges(Long cpf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canUserEnrollInMoreChallenges(Long cpf) {
		// TODO Auto-generated method stub
		return false;
	}

    /*@Override
    public List<CupomDesconto> viewChallengeRewards(Long desafioId) {
        Desafio desafio = desafioRepository.findById(desafioId)
                .orElseThrow(() -> new RuntimeException("Desafio não encontrado"));
        return desafio.getCupons();
    }*/
}

