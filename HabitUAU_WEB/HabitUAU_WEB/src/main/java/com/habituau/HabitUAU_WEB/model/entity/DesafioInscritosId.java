package com.habituau.HabitUAU_WEB.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class DesafioInscritosId implements Serializable {

    private String cpfCliente;
    private Long idDesafio;

    public DesafioInscritosId() {}

    public DesafioInscritosId(String cpfCliente, Long idDesafio) {
        this.cpfCliente = cpfCliente;
        this.idDesafio = idDesafio;
    }

    // Getters e Setters
    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
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
        DesafioInscritosId that = (DesafioInscritosId) o;
        return Objects.equals(cpfCliente, that.cpfCliente) && 
               Objects.equals(idDesafio, that.idDesafio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpfCliente, idDesafio);
    }
}