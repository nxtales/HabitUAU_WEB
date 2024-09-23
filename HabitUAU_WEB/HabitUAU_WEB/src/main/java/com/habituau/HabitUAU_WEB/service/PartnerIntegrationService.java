package com.habituau.HabitUAU_WEB.service;

public interface PartnerIntegrationService {

    //List<Discount> getPartnerDiscounts(Long parceiroId);

    void redeemPartnerDiscount(Long cpf, Long discountId);
}
