package com.habituau.HabitUAU_WEB.model.entity;

import jakarta.persistence.*;

@Entity
@IdClass(DesafioInscritosId.class)
public class DesafioInscrito {

    @Id
    @Column(name = "CPF_cliente")
    private String CPF_cliente;  // This must match the IdClass field

    @Id
    @Column(name = "ID_Desafio")
    private Long ID_Desafio;  // This must match the IdClass field

    @ManyToOne
    @JoinColumn(name = "CPF_cliente", insertable = false, updatable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "ID_Desafio", insertable = false, updatable = false)
    private Desafio desafio;

    // Getters and setters
    public String getCpfCliente() {
        return CPF_cliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.CPF_cliente = cpfCliente;
    }

    public Long getIdDesafio() {
        return ID_Desafio;
    }

    public void setIdDesafio(Long idDesafio) {
        this.ID_Desafio = idDesafio;
    }

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
