package com.habituau.HabitUAU_WEB.service;
import java.util.List;

import com.habituau.HabitUAU_WEB.model.entity.*;

public interface ChallengeService {

    Desafio createChallenge(Long parceiroId, Long categoriaId, String nome, List<DesafioTarefa> tasks);

    //List<Desafio> suggestChallengesForUser(Long cpf);//em uma implementação anterior, usava atributos do cliente que não existem mais... ainda estando fazer com input

    List<Desafio> getUserChallenges(String cpf);

    boolean canUserEnrollInMoreChallenges(String cpf);

	List<Desafio> suggestChallengesForUser(String searchInput);

}
