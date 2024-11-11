package com.habituau.HabitUAU_WEB.api.dto;

public class ParceiroDTO {

    private Long id;
    private Long segmentoId;
    private int qtdeDesafios;
    private byte[] foto;
    private String nome;

    // Construtor vazio
    public ParceiroDTO() {}

    // Construtor com todos os campos
    public ParceiroDTO(Long id, Long segmentoId, int qtdeDesafios, String nome, byte[] foto) {
        this.id = id;
        this.segmentoId = segmentoId;
        this.qtdeDesafios = qtdeDesafios;
        this.foto = foto;
        this.nome = nome;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSegmentoId() {
        return segmentoId;
    }

    public void setSegmentoId(Long segmentoId) {
        this.segmentoId = segmentoId;
    }

    public int getQtdeDesafios() {
        return qtdeDesafios;
    }

    public void setQtdeDesafios(int qtdeDesafios) {
        this.qtdeDesafios = qtdeDesafios;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

