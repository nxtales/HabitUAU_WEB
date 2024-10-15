package com.habituau.HabitUAU_WEB.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class DesafioInscritosTarefasCompletasId implements Serializable {
    
	private String cliente;  // Deve ter o mesmo nome do campo na entidade
    private Integer tarefa;  // Mesmo nome do campo na entidade
    private Integer desafio; // Mesmo nome do campo na entidade

    // Construtores
    public DesafioInscritosTarefasCompletasId() {}

    public DesafioInscritosTarefasCompletasId(String cliente, Integer tarefa, Integer desafio) {
        this.cliente = cliente;
        this.tarefa = tarefa;
        this.desafio = desafio;
    }
    
    // Getters e Setters
    public String getCpfCliente() {
        return cliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cliente = cpfCliente;
    }

    public Integer getIdTarefa() {
        return tarefa;
    }

    public void setIdTarefa(Integer idTarefa) {
        this.tarefa = idTarefa;
    }

    public Integer getIdDesafio() {
        return desafio;
    }

    public void setIdDesafio(Integer idDesafio) {
        this.desafio = idDesafio;
    }

    // Métodos equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DesafioInscritosTarefasCompletasId)) return false;
        DesafioInscritosTarefasCompletasId that = (DesafioInscritosTarefasCompletasId) o;
        return Objects.equals(cliente, that.cliente) &&
               Objects.equals(tarefa, that.tarefa) &&
               Objects.equals(desafio, that.desafio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, tarefa, desafio);
    }
}
