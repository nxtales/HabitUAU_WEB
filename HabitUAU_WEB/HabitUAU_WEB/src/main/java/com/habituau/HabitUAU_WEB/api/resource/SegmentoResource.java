package com.habituau.HabitUAU_WEB.api.resource;

import com.habituau.HabitUAU_WEB.api.dto.SegmentoDTO;
import com.habituau.HabitUAU_WEB.model.entity.Segmento;
import com.habituau.HabitUAU_WEB.service.SegmentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/segmentos")
public class SegmentoResource {

    @Autowired
    private SegmentoService segmentoService;

    // Endpoint para criar um novo segmento
    @PostMapping("/create")
    public ResponseEntity<SegmentoDTO> criarSegmento(@RequestBody SegmentoDTO segmentoDTO) {
        Segmento segmento = new Segmento();
        segmento.setNome(segmentoDTO.getNome());

        Segmento novoSegmento = segmentoService.criarSegmento(segmento);
        SegmentoDTO responseDTO = new SegmentoDTO(novoSegmento.getId(), novoSegmento.getNome());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // Endpoint para excluir um segmento pelo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> excluirSegmento(@PathVariable Long id) {
        segmentoService.excluirSegmento(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para atualizar um segmento pelo ID
    @PutMapping("/update/{id}")
    public ResponseEntity<SegmentoDTO> atualizarSegmento(@PathVariable Long id, @RequestBody SegmentoDTO segmentoDTO) {
        Segmento segmentoAtualizado = new Segmento();
        segmentoAtualizado.setNome(segmentoDTO.getNome());

        Segmento segmento = segmentoService.alterarSegmento(id, segmentoAtualizado);
        SegmentoDTO responseDTO = new SegmentoDTO(segmento.getId(), segmento.getNome());

        return ResponseEntity.ok(responseDTO);
    }

    // Endpoint para consultar um segmento pelo ID
    @GetMapping("/get/{id}")
    public ResponseEntity<SegmentoDTO> consultarSegmento(@PathVariable Long id) {
        Optional<Segmento> segmentoOpt = segmentoService.consultarSegmento(id);
        if (segmentoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Segmento segmento = segmentoOpt.get();
        SegmentoDTO responseDTO = new SegmentoDTO(segmento.getId(), segmento.getNome());

        return ResponseEntity.ok(responseDTO);
    }

    // Endpoint para listar todos os segmentos
    @GetMapping("/list")
    public ResponseEntity<List<SegmentoDTO>> listarSegmentos() {
        List<SegmentoDTO> segmentos = segmentoService.listarSegmentos().stream()
                .map(segmento -> new SegmentoDTO(segmento.getId(), segmento.getNome()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(segmentos);
    }
}

