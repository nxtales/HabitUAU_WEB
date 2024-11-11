package com.habituau.HabitUAU_WEB.api.resource;

import com.habituau.HabitUAU_WEB.api.dto.CategoriaDesafioDTO;
import com.habituau.HabitUAU_WEB.model.entity.CategoriaDesafio;
import com.habituau.HabitUAU_WEB.service.CategoriaDesafioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaDesafioResource {

    @Autowired
    private CategoriaDesafioService categoriaService;

    // Endpoint para criar uma nova categoria de desafio
    @PostMapping("/create")
    public ResponseEntity<CategoriaDesafioDTO> criarCategoria(@RequestBody CategoriaDesafioDTO categoriaDTO) {
        CategoriaDesafio categoria = new CategoriaDesafio();
        categoria.setNome(categoriaDTO.getNome());

        CategoriaDesafio novaCategoria = categoriaService.criarCategoria(categoria);
        CategoriaDesafioDTO responseDTO = new CategoriaDesafioDTO(novaCategoria.getId(), novaCategoria.getNome());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // Endpoint para excluir uma categoria pelo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> excluirCategoria(@PathVariable Long id) {
        categoriaService.excluirCategoria(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para atualizar uma categoria pelo ID
    @PutMapping("/update/{id}")
    public ResponseEntity<CategoriaDesafioDTO> atualizarCategoria(@PathVariable Long id, @RequestBody CategoriaDesafioDTO categoriaDTO) {
        CategoriaDesafio categoriaAtualizada = new CategoriaDesafio();
        categoriaAtualizada.setNome(categoriaDTO.getNome());

        CategoriaDesafio categoria = categoriaService.alterarCategoria(id, categoriaAtualizada);
        CategoriaDesafioDTO responseDTO = new CategoriaDesafioDTO(categoria.getId(), categoria.getNome());

        return ResponseEntity.ok(responseDTO);
    }

    // Endpoint para consultar uma categoria pelo ID
    @GetMapping("/get/{id}")
    public ResponseEntity<CategoriaDesafioDTO> consultarCategoria(@PathVariable Long id) {
        Optional<CategoriaDesafio> categoriaOpt = categoriaService.consultarCategoria(id);
        if (categoriaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        CategoriaDesafio categoria = categoriaOpt.get();
        CategoriaDesafioDTO responseDTO = new CategoriaDesafioDTO(categoria.getId(), categoria.getNome());

        return ResponseEntity.ok(responseDTO);
    }

    // Endpoint para listar todas as categorias de desafios
    @GetMapping("/list")
    public ResponseEntity<List<CategoriaDesafioDTO>> listarCategorias() {
        List<CategoriaDesafioDTO> categorias = categoriaService.listarCategorias().stream()
                .map(categoria -> new CategoriaDesafioDTO(categoria.getId(), categoria.getNome()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(categorias);
    }
}

