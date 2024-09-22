package com.habituau.HabitUAU_WEB.model.entity;
import jakarta.persistence.*;

@Entity
public class Desafio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parceiro_id")
    private Parceiro parceiro;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaDesafio categoria;

    private String nome;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Parceiro getParceiro() {
        return parceiro;
    }

    public void setParceiro(Parceiro parceiro) {
        this.parceiro = parceiro;
    }

    public CategoriaDesafio getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDesafio categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
