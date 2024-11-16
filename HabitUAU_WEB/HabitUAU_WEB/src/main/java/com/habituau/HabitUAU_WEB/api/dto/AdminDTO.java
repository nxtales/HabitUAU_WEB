package com.habituau.HabitUAU_WEB.api.dto;

public class AdminDTO {
    private Long RE;
    private String email;
    private String Nome;
    private String Sobrenome;
    private Long filialId;
    private String senha;
    private String telefone;

    // Getters e Setters
    public Long getRE() {
        return RE;
    }

    public void setRE(Long RE) {
        this.RE = RE;
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

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getSobrenome() {
        return Sobrenome;
    }

    public void setSobrenome(String Sobrenome) {
        this.Sobrenome = Sobrenome;
    }

    public Long getFilialId() {
        return filialId;
    }

    public void setFilialId(Long filialId) {
        this.filialId = filialId;
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
