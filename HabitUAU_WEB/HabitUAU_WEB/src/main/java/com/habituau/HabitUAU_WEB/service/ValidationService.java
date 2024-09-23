package com.habituau.HabitUAU_WEB.service;

public interface ValidationService {

	void validateChallenge(Long cpf, Long desafioId, boolean validado);

	boolean isChallengeValidated(Long cpf, Long desafioId);

}
