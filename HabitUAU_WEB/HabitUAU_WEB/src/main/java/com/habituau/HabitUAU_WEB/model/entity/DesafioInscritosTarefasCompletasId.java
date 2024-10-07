package com.habituau.HabitUAU_WEB.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class DesafioInscritosTarefasCompletasId implements Serializable {
    
    private String cpfCliente; // CPF_cliente
    private Integer idTarefa;  // ID_tarefa
    private Integer idDesafio; // ID_Desafio

    // Construtores
    public DesafioInscritosTarefasCompletasId() {}

    public DesafioInscritosTarefasCompletasId(String cpfCliente, Integer idTarefa, Integer idDesafio) {
        this.cpfCliente = cpfCliente;
        this.idTarefa = idTarefa;
        this.idDesafio = idDesafio;
    }

    // Getters e Setters
    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public Integer getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(Integer idTarefa) {
        this.idTarefa = idTarefa;
    }

    public Integer getIdDesafio() {
        return idDesafio;
    }

    public void setIdDesafio(Integer idDesafio) {
        this.idDesafio = idDesafio;
    }

    // Métodos equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DesafioInscritosTarefasCompletasId)) return false;
        DesafioInscritosTarefasCompletasId that = (DesafioInscritosTarefasCompletasId) o;
        return Objects.equals(cpfCliente, that.cpfCliente) &&
               Objects.equals(idTarefa, that.idTarefa) &&
               Objects.equals(idDesafio, that.idDesafio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpfCliente, idTarefa, idDesafio);
    }
}
