package com.habituau.HabitUAU_WEB.model.entity;

import jakarta.persistence.*;

@Entity
public class Admin {

    @Id
    private Long RE;

    @Column(unique = true)
    private String email;

    private String Nome;
    private String Sobrenome;

    @ManyToOne
    @JoinColumn(name = "filial_id")
    private Filial filial;

    private String senha;
    private String telefone;

    // Getters e Setters
    public Long getRe() {
        return RE;
    }

    public void setRe(Long re) {
        this.RE = re;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }

    public String getSobrenome() {
        return Sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.Sobrenome = sobrenome;
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
