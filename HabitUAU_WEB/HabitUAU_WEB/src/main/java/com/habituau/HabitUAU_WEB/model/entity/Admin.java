package com.habituau.HabitUAU_WEB.model.entity;

import jakarta.persistence.*;

@Entity
public class Admin {

    @Id
    private Long re;

    @Column(unique = true)
    private String email;

    private String nome;
    private String sobrenome;

    @ManyToOne
    @JoinColumn(name = "filial_id")
    private Filial filial;

    private String senha;
    private String telefone;

    // Getters e Setters
    public Long getRe() {
        return re;
    }

    public void setRe(Long re) {
        this.re = re;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
