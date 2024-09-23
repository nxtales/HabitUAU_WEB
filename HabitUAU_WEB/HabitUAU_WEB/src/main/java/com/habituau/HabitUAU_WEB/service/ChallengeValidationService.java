package com.habituau.HabitUAU_WEB.service;
import com.habituau.HabitUAU_WEB.model.entity.*;

public interface ChallengeValidationService {

    void submitChallengeCompletion(Long cpf, Long desafioId, String nomeTarefa, byte[] photo);

    void validateChallengeTask(Long tarefaId, boolean isValid);

    boolean isTaskValidated(Long tarefaId);

    void notifyUserTaskRejection(Long cpf, Long tarefaId);
}