package com.habituau.HabitUAU_WEB.model.entity;
import java.util.List;

import com.habituau.HabitUAU_WEB.api.dto.TarefaDTO;
import com.habituau.HabitUAU_WEB.model.repository.CategoriasDesafiosRepository;
import com.habituau.HabitUAU_WEB.model.repository.ParceiroRepository;

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
    
    public Desafio(Long ID, Parceiro parceiro, CategoriaDesafio categoria, String nome, List<DesafioTarefa> tasks) {
        this.ID = ID;
        this.parceiro = parceiro;
        this.categoria = categoria;
        this.Nome = nome;
       //this.tarefas = tasks;
    }

    // Construtor sem ID (para criação), recebendo Parceiro e CategoriaDesafio como objetos
    public Desafio(Parceiro parceiro, CategoriaDesafio categoria, String nome, List<DesafioTarefa> tasks) {
        this.parceiro = parceiro;
        this.categoria = categoria;
        this.Nome = nome;
        //this.tasks = tasks;
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
