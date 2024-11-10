package com.habituau.HabitUAU_WEB.api.dto;

public class TarefaDTO {
    private Long id;
    private String nome;
    private boolean completada;

    // Construtores, getters e setters

    public TarefaDTO() {}

    public TarefaDTO(Long id, String nome, boolean completada) {
        this.id = id;
        this.nome = nome;
        this.completada = completada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }
}