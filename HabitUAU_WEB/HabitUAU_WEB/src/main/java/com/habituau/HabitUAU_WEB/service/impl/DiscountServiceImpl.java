//ESSA CLASSE TB NÃO FUNCIONA, PRECISO CRIAR O BANCO DE CUPONS PRIMEIRO


package com.habituau.HabitUAU_WEB.service.impl;

import com.habituau.HabitUAU_WEB.model.entity.DesafioInscrito;
import com.habituau.HabitUAU_WEB.model.repository.DesafioInscritosRepository;
import com.habituau.HabitUAU_WEB.model.repository.ParceiroRepository;
import com.habituau.HabitUAU_WEB.service.DiscountService;

/*@Service
public class /*DiscountServiceImpl implements DiscountService {

    private final ParceiroRepository parceiroRepository;
    private final DesafioInscritosRepository desafioInscritosRepository;

    @Autowired
        public DiscountServiceImpl(ParceiroRepository parceiroRepository, DesafioInscritosRepository desafioInscritosRepository) {
        this.parceiroRepository = parceiroRepository;
        this.desafioInscritosRepository = desafioInscritosRepository;
    }

    @Override
    public void assignDiscountAfterChallenge(Long cpf, Long desafioId) {
        if (!isValidCPF(cpf)) {
            throw new IllegalArgumentException("CPF inválido");
        }

        DesafioInscrito desafioInscrito = desafioInscritosRepository.findById(new DesafioInscritosId(cpf, desafioId))
                .orElseThrow(() -> new RuntimeException("Desafio não encontrado"));

        if (desafioInscrito.isValidado()) {
            Parceiro parceiro = parceiroRepository.findPartnerByChallenge(desafioId);
            // Lógica de geração do cupom
            parceiro.gerarCupomDesconto();
        } else {
            throw new RuntimeException("O desafio ainda não foi validado.");
        }
    }

    @Override
    public List<CupomDesconto> getAvailableDiscounts(Long cpf) {
        if (!isValidCPF(cpf)) {
            throw new IllegalArgumentException("CPF inválido");
        }
        return parceiroRepository.findCuponsByClientId(cpf);
    }

    // Validação de CPF (somente números e quantidade correta de dígitos)
    private boolean isValidCPF(Long cpf) {
        String cpfString = String.valueOf(cpf);
        return cpfString.matches("\\d{11}");
    }
}*/

