package com.habituau.HabitUAU_WEB.model.entity;

import jakarta.persistence.*;

@Entity
public class DesafioTarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarefa;

    @ManyToOne
    @JoinColumn(name = "desafio_id")
    private Desafio desafio;

    private String nomeTarefa;
    private int qtdePontos;

    // Getters e Setters
    public Long getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(Long idTarefa) {
        this.idTarefa = idTarefa;
    }

    public Desafio getDesafio() {
        return desafio;
    }

    public void setDesafio(Desafio desafio) {
        this.desafio = desafio;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }

    public int getQtdePontos() {
        return qtdePontos;
    }

    public void setQtdePontos(int qtdePontos) {
        this.qtdePontos = qtdePontos;
    }
}
