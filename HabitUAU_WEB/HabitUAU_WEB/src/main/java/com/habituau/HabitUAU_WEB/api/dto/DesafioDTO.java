package com.habituau.HabitUAU_WEB.api.dto;

import java.util.List;

public class DesafioDTO {

    private Long id;
    private Long parceiroId;
    private Long categoriaId;
    private String nome;
    private List<TarefaDTO> tarefas;

    // Construtor vazio
    public DesafioDTO() {}

    // Construtor com todos os atributos
    public DesafioDTO(Long id, Long parceiroId, Long categoriaId, String nome, List<TarefaDTO> tarefas) {
        this.id = id;
        this.parceiroId = parceiroId;
        this.categoriaId = categoriaId;
        this.nome = nome;
        this.tarefas = tarefas;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParceiroId() {
        return parceiroId;
    }

    public void setParceiroId(Long parceiroId) {
        this.parceiroId = parceiroId;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<TarefaDTO> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<TarefaDTO> tarefas) {
        this.tarefas = tarefas;
    }
}
