package com.habituau.HabitUAU_WEB.model.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@IdClass(AmizadeId.class)
public class Amizade {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_cli1")
    private Cliente cliente1;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_cli2")
    private Cliente cliente2;

    @Temporal(TemporalType.DATE)
    private Date dataAmizade;

    // Getters e Setters
    public Cliente getCliente1() {
        return cliente1;
    }

    public void setCliente1(Cliente cliente1) {
        this.cliente1 = cliente1;
    }

    public Cliente getCliente2() {
        return cliente2;
    }

    public void setCliente2(Cliente cliente2) {
        this.cliente2 = cliente2;
    }

    public Date getDataAmizade() {
        return dataAmizade;
    }

    public void setDataAmizade(Date dataAmizade) {
        this.dataAmizade = dataAmizade;
    }
}