package com.habituau.HabitUAU_WEB.model.entity;

import jakarta.persistence.*;

@Entity
public class DesafioTarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarefa") 
    private Long ID;

    @ManyToOne
    @JoinColumn(name = "id_desafio")
    private Desafio desafio;

    @Column(name = "nome_tarefa")
    private String Nome_tarefa;
    
    @Column(name = "qtde_pontos")
    private Long qtde_pontos;
    
    public DesafioTarefa() {}

    public DesafioTarefa(Long id, String nome, Long qtdePontos, Desafio desafio) {
        this.ID = id;
        this.Nome_tarefa = nome;
        this.qtde_pontos = qtdePontos;
        this.desafio = desafio;
    }

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

    public Long getqtde_pontos() {
        return qtde_pontos;
    }

    public void setqtde_pontos(Long qtde_pontos) {
        this.qtde_pontos = qtde_pontos;
    }
}
