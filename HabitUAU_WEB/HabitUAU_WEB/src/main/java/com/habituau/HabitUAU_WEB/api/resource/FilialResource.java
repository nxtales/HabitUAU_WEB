package com.habituau.HabitUAU_WEB.api.resource;

import com.habituau.HabitUAU_WEB.api.dto.FilialDTO;
import com.habituau.HabitUAU_WEB.exceptions.RegraNegocioException;
import com.habituau.HabitUAU_WEB.model.entity.Filial;
import com.habituau.HabitUAU_WEB.service.FilialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/filial")
public class FilialResource {

    @Autowired
    private FilialService filialService;

    @PostMapping("/create")
    public ResponseEntity<?> createFilial(@RequestBody FilialDTO dto) {
        Filial filial = filialService.createFilial(dto.getCidade(), dto.getCEP(), dto.getEndereco(), dto.getNome());
        return new ResponseEntity<>(filial, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateFilial(@PathVariable("id") Long ID, @RequestBody FilialDTO dto) {
        try {
            filialService.updateFilial(ID, dto.getCidade(), dto.getCEP(), dto.getEndereco(), dto.getNome());
            return ResponseEntity.ok("Filial atualizada com sucesso.");
        } catch (RegraNegocioException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFilial(@PathVariable("id") Long ID) {
        try {
            filialService.deleteFilial(ID);
            return ResponseEntity.noContent().build();
        } catch (RegraNegocioException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<?> retrieveFilialById(@PathVariable("id") Long ID) {
        Optional<Filial> filialOpt = filialService.findById(ID);
        if (filialOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filial com ID " + ID + " não encontrada.");
        }
        return ResponseEntity.ok(filialOpt.get());
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Filial>> getAllFiliais() {
        return ResponseEntity.ok(filialService.findAll());
    }
}
