package com.habituau.HabitUAU_WEB.model.entity;

import jakarta.persistence.*;

@Entity
public class Parceiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "segmento_id")
    private Segmento segmento;

    private int qtdeDesafios;

    @Lob
    private byte[] foto; // Foto do parceiro

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Segmento getSegmento() {
        return segmento;
    }

    public void setSegmento(Segmento segmento) {
        this.segmento = segmento;
    }

    public int getQtdeDesafios() {
        return qtdeDesafios;
    }

    public void setQtdeDesafios(int qtdeDesafios) {
        this.qtdeDesafios = qtdeDesafios;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}