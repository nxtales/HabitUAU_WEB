package com.habituau.HabitUAU_WEB.api.resource;

import com.habituau.HabitUAU_WEB.api.dto.AmizadeDTO;
import com.habituau.HabitUAU_WEB.service.AmizadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/amizades")
public class AmizadeResource {

    @Autowired
    private AmizadeService amizadeService;

    // Endpoint para criar uma nova amizade entre dois clientes
    @PostMapping("/create")
    public ResponseEntity<AmizadeDTO> criarAmizade(@RequestParam String cpfCliente1, @RequestParam String cpfCliente2) {
        AmizadeDTO amizadeDTO = amizadeService.criarAmizade(cpfCliente1, cpfCliente2);
        return ResponseEntity.status(HttpStatus.CREATED).body(amizadeDTO);
    }

    // Endpoint para buscar todas as amizades de um cliente específico pelo CPF
    @GetMapping("/cliente/{cpf}")
    public ResponseEntity<List<AmizadeDTO>> buscarAmizadesPorCliente(@PathVariable String cpf) {
        List<AmizadeDTO> amizades = amizadeService.buscarAmizadesPorCliente(cpf);
        return ResponseEntity.ok(amizades);
    }
}
