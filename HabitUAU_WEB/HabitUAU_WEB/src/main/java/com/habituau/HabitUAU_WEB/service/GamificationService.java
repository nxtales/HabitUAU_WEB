package com.habituau.HabitUAU_WEB.service;

public interface GamificationService {

    void assignPoints(Long cpf, Long desafioId, int pontos);

    //void updateUserLevel(Long cpf);

    //void assignDiscountReward(Long cpf, Discount discount);

    int getUserPoints(Long cpf);

    int getChallengePoints(Long desafioId);
}
