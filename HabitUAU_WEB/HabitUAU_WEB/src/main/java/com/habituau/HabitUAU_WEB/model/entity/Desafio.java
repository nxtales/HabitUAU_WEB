package com.habituau.HabitUAU_WEB.model.entity;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Desafio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @ManyToOne
    @JoinColumn(name = "ID_parceiro")
    private Parceiro parceiro;

    @ManyToOne
    @JoinColumn(name = "ID_categoria")
    private CategoriaDesafio categoria;

    private String Nome;

    public Desafio(Long parceiroId, Long categoriaId, String nome2, List<DesafioTarefa> tasks) {
		// TODO Auto-generated constructor stub
	}

	// Getters e Setters
    public Long getId() {
        return ID;
    }

    public void setId(Long id) {
        this.ID = id;
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
        return Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }
}
