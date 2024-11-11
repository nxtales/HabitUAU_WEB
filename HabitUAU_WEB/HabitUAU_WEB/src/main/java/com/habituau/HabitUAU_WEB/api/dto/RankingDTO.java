package com.habituau.HabitUAU_WEB.api.dto;

public class RankingDTO {

    private String cpfCliente;
    private String nomeCliente;
    private int pontos;
    private Long desafioId;

    // Construtor completo
    public RankingDTO(String cpfCliente, String nomeCliente, int pontos, Long desafioId) {
        this.cpfCliente = cpfCliente;
        this.nomeCliente = nomeCliente;
        this.pontos = pontos;
        this.desafioId = desafioId;
    }

    // Getters e Setters
    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public Long getDesafioId() {
        return desafioId;
    }

    public void setDesafioId(Long desafioId) {
        this.desafioId = desafioId;
    }
}
