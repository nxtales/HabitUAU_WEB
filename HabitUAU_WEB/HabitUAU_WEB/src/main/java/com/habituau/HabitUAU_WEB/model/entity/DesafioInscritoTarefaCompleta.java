package com.habituau.HabitUAU_WEB.model.entity;

import jakarta.persistence.*;

@Entity
@IdClass(DesafioInscritosTarefasCompletasId.class)
public class DesafioInscritoTarefaCompleta {

    @Id
    @ManyToOne
    @JoinColumn(name = "cpf_cliente", referencedColumnName = "cpf")
    private Cliente cliente;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_tarefa", referencedColumnName = "ID_Tarefa")
    private DesafioTarefa tarefa;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_desafio", referencedColumnName = "ID")
    private Desafio desafio;

    private int sumPontos;

    private String cpfCliente;
    private Long idDesafio;
    private Long idTarefa;
    
   
    
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

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public Long getIdTarefa() {
		return idTarefa;
	}

	public void setIdTarefa(Long idTarefa) {
		this.idTarefa = idTarefa;
	}

	public Long getIdDesafio() {
		return idDesafio;
	}

	public void setIdDesafio(Long idDesafio) {
		this.idDesafio = idDesafio;
	}
}
