package com.habituau.HabitUAU_WEB.api.dto;

public class TarefaDTO {
    private Long id;
    private String nome;
    private boolean completada;
    private int qtdepontos;
    private Long idDesafio; // Novo campo para ID do desafio

    // Construtores, getters e setters

    public TarefaDTO() {}

    public TarefaDTO(Long id, String nome, int qtdepontos, boolean completada, Long idDesafio) {
        this.id = id;
        this.nome = nome;
        this.qtdepontos = qtdepontos;
        this.completada = completada;
        this.idDesafio = idDesafio;
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

    public int getQtdepontos() {
        return qtdepontos;
    }

    public void setQtdepontos(int qtdepontos) {
        this.qtdepontos = qtdepontos;
    }

    public Long getIdDesafio() {
        return idDesafio;
    }

    public void setIdDesafio(Long idDesafio) {
        this.idDesafio = idDesafio;
    }
}
