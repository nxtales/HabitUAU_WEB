package com.habituau.HabitUAU_WEB.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class DesafioInscritosId implements Serializable {

    private String CPF_cliente;  // Primary key of Cliente
    private Long ID_Desafio;     // Primary key of Desafio

    public DesafioInscritosId() {}

    public DesafioInscritosId(String CPF_cliente, Long ID_Desafio) {
        this.CPF_cliente = CPF_cliente;
        this.ID_Desafio = ID_Desafio;
    }

    // Getters and setters
    public String getCPF_cliente() {
        return CPF_cliente;
    }

    public void setCPF_cliente(String CPF_cliente) {
        this.CPF_cliente = CPF_cliente;
    }

    public Long getID_Desafio() {
        return ID_Desafio;
    }

    public void setID_Desafio(Long ID_Desafio) {
        this.ID_Desafio = ID_Desafio;
    }

    // equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DesafioInscritosId that = (DesafioInscritosId) o;
        return Objects.equals(CPF_cliente, that.CPF_cliente) &&
               Objects.equals(ID_Desafio, that.ID_Desafio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CPF_cliente, ID_Desafio);
    }
}
