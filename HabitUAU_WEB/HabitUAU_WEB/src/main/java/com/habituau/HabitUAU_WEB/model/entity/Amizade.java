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

    @Column(name = "idcli1")
    private String idCli1; // Coluna adicional

    @Column(name = "idcli2")
    private String idCli2; // Coluna adicional

    public Amizade() {
    }

    // Ciclo de vida para sincronizar os valores
    @PrePersist
    @PreUpdate
    public void syncIds() {
        if (cliente1 != null) {
            this.idCli1 = cliente1.getCpf(); // Garante que idcli1 receba o CPF de cliente1
        }
        if (cliente2 != null) {
            this.idCli2 = cliente2.getCpf(); // Garante que idcli2 receba o CPF de cliente2
        }
    }

    // Getters e Setters
    public Cliente getCliente1() {
        return cliente1;
    }

    public void setCliente1(Cliente cliente1) {
        this.cliente1 = cliente1;
        this.idCli1 = cliente1 != null ? cliente1.getCpf() : null; // Sincroniza durante o setter
    }

    public Cliente getCliente2() {
        return cliente2;
    }

    public void setCliente2(Cliente cliente2) {
        this.cliente2 = cliente2;
        this.idCli2 = cliente2 != null ? cliente2.getCpf() : null; // Sincroniza durante o setter
    }

    public Date getDataAmizade() {
        return dataAmizade;
    }

    public void setDataAmizade(Date dataAmizade) {
        this.dataAmizade = dataAmizade;
    }

    public String getIdCli1() {
        return idCli1;
    }

    public void setIdCli1(String idCli1) {
        this.idCli1 = idCli1;
    }

    public String getIdCli2() {
        return idCli2;
    }

    public void setIdCli2(String idCli2) {
        this.idCli2 = idCli2;
    }
}
