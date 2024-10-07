package com.habituau.HabitUAU_WEB.model.entity;

import jakarta.persistence.*;

@Entity
@IdClass(DesafioInscritosId.class)
public class DesafioInscrito {

    @Id
    @Column(name = "cpf_cliente")
    private String cpfCliente;  // This must match the IdClass field

    @Id
    @Column(name = "desafio_id")
    private Long idDesafio;  // This must match the IdClass field

    @ManyToOne
    @JoinColumn(name = "cpf_cliente", insertable = false, updatable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "desafio_id", insertable = false, updatable = false)
    private Desafio desafio;

    // Getters and setters
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
