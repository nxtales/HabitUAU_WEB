//precisa colocar apis pra enviar de fato as notificações por e-mail se for possível - nat 

package com.habituau.HabitUAU_WEB.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habituau.HabitUAU_WEB.model.entity.Cliente;
import com.habituau.HabitUAU_WEB.model.repository.ClienteRepository;
import com.habituau.HabitUAU_WEB.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public NotificationServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void sendChallengeReminder(Long cpf) {
        if (!isValidCPF(cpf)) {
            throw new IllegalArgumentException("CPF inválido");
        }

        Cliente cliente = clienteRepository.findByCPF(cpf.toString())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        // Lógica de envio de notificação
        System.out.println("Notificação de lembrete de desafio enviada para o usuário: " + cliente.getEmail());
    }

    /*@Override
    public void sendNewCouponNotification(Long cpf) {
        if (!isValidCPF(cpf)) {
            throw new IllegalArgumentException("CPF inválido");
        }

        Cliente cliente = clienteRepository.findById(cpf)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        // Lógica de envio de notificação
        System.out.println("Notificação de novo cupom enviada para o usuário: " + cliente.getEmail());
    }*/

    // Validação de CPF
    private boolean isValidCPF(Long cpf) {
        String cpfString = String.valueOf(cpf);
        return cpfString.matches("\\d{11}");
    }

	@Override
	public void sendProgressNotification(Long cpf, Long desafioId) {
		if (!isValidCPF(cpf)) {
            throw new IllegalArgumentException("CPF inválido");
        }

        Cliente cliente = clienteRepository.findByCPF(cpf.toString())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        // Lógica de envio de notificação
        System.out.println("Notificação de progresso enviada para o usuário: " + cliente.getEmail());
	}
}

