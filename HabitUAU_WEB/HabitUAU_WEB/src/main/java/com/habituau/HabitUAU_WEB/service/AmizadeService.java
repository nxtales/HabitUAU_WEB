package com.habituau.HabitUAU_WEB.service;

import com.habituau.HabitUAU_WEB.api.dto.AmizadeDTO;
import java.util.List;

public interface AmizadeService {
    AmizadeDTO criarAmizade(String cpfCliente1, String cpfCliente2);
    List<AmizadeDTO> buscarAmizadesPorCliente(String cpf);
}

