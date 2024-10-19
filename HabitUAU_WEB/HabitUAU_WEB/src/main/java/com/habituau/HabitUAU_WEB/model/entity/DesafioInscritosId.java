package com.habituau.HabitUAU_WEB.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class DesafioInscritosId implements Serializable {

    private String cpf_cliente;  // Primary key of Cliente
    private Long ID_Desafio;     // Primary key of Desafio

    public DesafioInscritosId() {}

    public DesafioInscritosId(String cpf_cliente, Long ID_Desafio) {
        this.cpf_cliente = cpf_cliente;
        this.ID_Desafio = ID_Desafio;
    }

    // Getters and setters
    public String getcpf_cliente() {
        return cpf_cliente;
    }

    public void setcpf_cliente(String cpf_cliente) {
        this.cpf_cliente = cpf_cliente;
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
        return Objects.equals(cpf_cliente, that.cpf_cliente) &&
               Objects.equals(ID_Desafio, that.ID_Desafio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf_cliente, ID_Desafio);
    }
}
