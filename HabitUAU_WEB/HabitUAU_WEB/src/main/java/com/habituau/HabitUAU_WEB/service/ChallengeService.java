package com.habituau.HabitUAU_WEB.service;
import java.util.List;

import com.habituau.HabitUAU_WEB.model.entity.*;

public interface ChallengeService {

    Desafio createChallenge(Long parceiroId, Long categoriaId, String nome, List<DesafioTarefa> tasks);

    List<Desafio> suggestChallengesForUser(Long cpf);

    List<Desafio> getUserChallenges(Long cpf);

    boolean canUserEnrollInMoreChallenges(Long cpf);
}
