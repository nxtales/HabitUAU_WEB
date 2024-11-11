package com.habituau.HabitUAU_WEB.api.dto;

public class CategoriaDesafioDTO {

    private Long id;
    private String nome;

    // Construtor vazio
    public CategoriaDesafioDTO() {}

    // Construtor com todos os campos
    public CategoriaDesafioDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Getters e Setters
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
}

