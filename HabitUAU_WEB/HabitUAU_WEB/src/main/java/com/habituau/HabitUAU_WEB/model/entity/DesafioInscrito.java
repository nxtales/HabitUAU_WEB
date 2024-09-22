package com.habituau.HabitUAU_WEB.model.entity;

import jakarta.persistence.*;

@Entity
@IdClass(DesafioInscritoId.class)
public class DesafioInscrito {

    @Id
    @ManyToOne
    @JoinColumn(name = "cpf_cliente")
    private Cliente cliente;

    @Id
    @ManyToOne
    @JoinColumn(name = "desafio_id")
    private Desafio desafio;

    // Getters e Setters
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Desafio getDesafio() {
        return desafio;
    }

    public void setDesafio(Desafio desafio) {
        this.desafio = desafio;
    }
}