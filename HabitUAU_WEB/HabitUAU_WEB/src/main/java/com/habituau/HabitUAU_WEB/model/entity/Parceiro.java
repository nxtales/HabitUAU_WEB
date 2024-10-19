package com.habituau.HabitUAU_WEB.model.entity;

import jakarta.persistence.*;

@Entity
public class Parceiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @ManyToOne
    @JoinColumn(name = "Segmento")
    private Segmento Segmento;

    private int qtde_desafios;

    @Lob
    private byte[] foto; // Foto do parceiro

    // Getters e Setters
    public Long getId() {
        return ID;
    }

    public void setId(Long id) {
        this.ID = id;
    }

    public Segmento getSegmento() {
        return Segmento;
    }

    public void setSegmento(Segmento segmento) {
        this.Segmento = segmento;
    }

    public int getqtde_desafios() {
        return qtde_desafios;
    }

    public void setqtde_desafios(int qtde_desafios) {
        this.qtde_desafios = qtde_desafios;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}