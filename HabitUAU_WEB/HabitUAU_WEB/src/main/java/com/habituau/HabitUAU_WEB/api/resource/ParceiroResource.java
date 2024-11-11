package com.habituau.HabitUAU_WEB.api.resource;

import com.habituau.HabitUAU_WEB.api.dto.ParceiroDTO;
import com.habituau.HabitUAU_WEB.model.entity.Parceiro;
import com.habituau.HabitUAU_WEB.model.entity.Segmento;
import com.habituau.HabitUAU_WEB.service.ParceiroService;
import com.habituau.HabitUAU_WEB.model.repository.SegmentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/parceiros")
public class ParceiroResource {

    @Autowired
    private ParceiroService parceiroService;

    @Autowired
    private SegmentosRepository segmentoRepository;

    // Endpoint para criar um novo parceiro
    @PostMapping("/create")
    public ResponseEntity<ParceiroDTO> criarParceiro(@RequestBody ParceiroDTO parceiroDTO) {
        Optional<Segmento> segmentoOpt = segmentoRepository.findById(parceiroDTO.getSegmentoId());
        if (segmentoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }

        Parceiro parceiro = new Parceiro();
        parceiro.setSegmento(segmentoOpt.get());
        parceiro.setqtde_desafios(parceiroDTO.getQtdeDesafios());
        parceiro.setFoto(parceiroDTO.getFoto());

        Parceiro novoParceiro = parceiroService.criarParceiro(parceiro);
        ParceiroDTO responseDTO = new ParceiroDTO(novoParceiro.getId(), parceiroDTO.getSegmentoId(),
                novoParceiro.getqtde_desafios(), novoParceiro.getFoto());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // Endpoint para excluir um parceiro pelo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> excluirParceiro(@PathVariable Long id) {
        parceiroService.excluirParceiro(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para atualizar um parceiro pelo ID
    @PutMapping("/update/{id}")
    public ResponseEntity<ParceiroDTO> atualizarParceiro(@PathVariable Long id, @RequestBody ParceiroDTO parceiroDTO) {
        Optional<Segmento> segmentoOpt = segmentoRepository.findById(parceiroDTO.getSegmentoId());
        if (segmentoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }

        Parceiro parceiroAtualizado = new Parceiro();
        parceiroAtualizado.setSegmento(segmentoOpt.get());
        parceiroAtualizado.setqtde_desafios(parceiroDTO.getQtdeDesafios());
        parceiroAtualizado.setFoto(parceiroDTO.getFoto());

        Parceiro parceiro = parceiroService.alterarParceiro(id, parceiroAtualizado);
        ParceiroDTO responseDTO = new ParceiroDTO(parceiro.getId(), parceiroDTO.getSegmentoId(),
                parceiro.getqtde_desafios(), parceiro.getFoto());

        return ResponseEntity.ok(responseDTO);
    }

    // Endpoint para consultar um parceiro pelo ID
    @GetMapping("/get/{id}")
    public ResponseEntity<ParceiroDTO> consultarParceiro(@PathVariable Long id) {
        Optional<Parceiro> parceiroOpt = parceiroService.consultarParceiro(id);
        if (parceiroOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Parceiro parceiro = parceiroOpt.get();
        ParceiroDTO responseDTO = new ParceiroDTO(parceiro.getId(), parceiro.getSegmento().getId(),
                parceiro.getqtde_desafios(), parceiro.getFoto());

        return ResponseEntity.ok(responseDTO);
    }

    // Endpoint para listar todos os parceiros
    @GetMapping("/list")
    public ResponseEntity<List<ParceiroDTO>> listarParceiros() {
        List<ParceiroDTO> parceiros = parceiroService.listarParceiros().stream()
                .map(parceiro -> new ParceiroDTO(parceiro.getId(), parceiro.getSegmento().getId(),
                        parceiro.getqtde_desafios(), parceiro.getFoto()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(parceiros);
    }
}

