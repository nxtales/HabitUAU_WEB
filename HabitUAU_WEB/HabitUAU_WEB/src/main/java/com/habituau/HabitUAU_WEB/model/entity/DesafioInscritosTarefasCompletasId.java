package com.habituau.HabitUAU_WEB.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class DesafioInscritosTarefasCompletasId implements Serializable {

    private String cpfCliente;
    private Long idTarefa;
    private Long idDesafio;

    public DesafioInscritosTarefasCompletasId() {}

    public DesafioInscritosTarefasCompletasId(String cpfCliente, Long idTarefa, Long idDesafio) {
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

    // equals() e hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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

