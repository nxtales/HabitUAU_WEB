package com.habituau.HabitUAU_WEB.service;

public interface NotificationService {

    //void sendChallengeReminder(Long cpf, Long desafioId);
    
    void sendChallengeReminder(Long cpf);

    //void sendNewDiscountNotification(Long cpf, Discount discount);

    void sendProgressNotification(Long cpf, Long desafioId);
}
