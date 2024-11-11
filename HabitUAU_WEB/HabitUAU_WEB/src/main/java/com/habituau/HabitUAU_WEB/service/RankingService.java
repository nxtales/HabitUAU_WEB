package com.habituau.HabitUAU_WEB.service;

import com.habituau.HabitUAU_WEB.api.dto.RankingDTO;
import java.util.List;
import java.util.Optional;

public interface RankingService {
    Optional<RankingDTO> getMaiorPontuacaoPorDesafio(String cpfCliente);
    List<RankingDTO> getRankingGeral();
}
