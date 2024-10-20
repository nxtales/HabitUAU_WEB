package com.habituau.HabitUAU_WEB.model.entity;

import jakarta.persistence.*;

@Entity
@IdClass(DesafioInscritosTarefasCompletasId.class)
public class DesafioInscritoTarefaCompleta {

	 @Id
	    @ManyToOne
	    @JoinColumn(name = "CPF_cliente", referencedColumnName = "CPF")
	    private Cliente cliente;

	    @Id
	    @ManyToOne
	    @JoinColumn(name = "ID_tarefa", referencedColumnName = "ID")
	    private DesafioTarefa tarefa;

	    @Id
	    @ManyToOne
	    @JoinColumn(name = "ID_desafio", referencedColumnName = "ID")
	    private Desafio desafio;

	    private int sumPontos;

	    // Getters e Setters
	    public Cliente getCliente() {
	        return cliente;
	    }

	    public void setCliente(Cliente cliente) {
	        this.cliente = cliente;
	    }

	    public DesafioTarefa getTarefa() {
	        return tarefa;
	    }

	    public void setTarefa(DesafioTarefa tarefa) {
	        this.tarefa = tarefa;
	    }

	    public Desafio getDesafio() {
	        return desafio;
	    }

	    public void setDesafio(Desafio desafio) {
	        this.desafio = desafio;
	    }

	    public int getSumPontos() {
	        return sumPontos;
	    }

	    public void setSumPontos(int sumPontos) {
	        this.sumPontos = sumPontos;
	    }
	}