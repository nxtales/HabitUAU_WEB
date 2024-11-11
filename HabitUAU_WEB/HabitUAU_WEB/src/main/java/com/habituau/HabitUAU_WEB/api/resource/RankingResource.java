package com.habituau.HabitUAU_WEB.api.resource;

import com.habituau.HabitUAU_WEB.api.dto.RankingDTO;
import com.habituau.HabitUAU_WEB.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ranking")
public class RankingResource {

    @Autowired
    private RankingService rankingService;

    // Endpoint para obter o desafio com maior pontuação para um cliente específico
    @GetMapping("/pordesafio/{cpfCliente}")
    public ResponseEntity<RankingDTO> getMaiorPontuacaoPorDesafio(@PathVariable String cpfCliente) {
        Optional<RankingDTO> rankingDTO = rankingService.getMaiorPontuacaoPorDesafio(cpfCliente);
        return rankingDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para ranking geral
    @GetMapping("/geral")
    public ResponseEntity<List<RankingDTO>> getRankingGeral() {
        List<RankingDTO> ranking = rankingService.getRankingGeral();
        return ResponseEntity.ok(ranking);
    }
}
