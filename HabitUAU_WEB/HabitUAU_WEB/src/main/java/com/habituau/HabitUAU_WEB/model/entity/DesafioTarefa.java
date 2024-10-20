package com.habituau.HabitUAU_WEB.model.entity;

import jakarta.persistence.*;

@Entity
public class DesafioTarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @ManyToOne
    @JoinColumn(name = "ID_Desafio")
    private Desafio desafio;

    private String Nome_tarefa;
    private int qtde_pontos;

    // Getters e Setters
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Desafio getDesafio() {
        return desafio;
    }

    public void setDesafio(Desafio desafio) {
        this.desafio = desafio;
    }

    public String getNome_tarefa() {
        return Nome_tarefa;
    }

    public void setNome_tarefa(String Nome_tarefa) {
        this.Nome_tarefa = Nome_tarefa;
    }

    public int getqtde_pontos() {
        return qtde_pontos;
    }

    public void setqtde_pontos(int qtde_pontos) {
        this.qtde_pontos = qtde_pontos;
    }
}
