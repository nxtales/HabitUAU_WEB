package com.habituau.HabitUAU_WEB.api.dto;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class ClienteDTO {


	private String CPF;

	//@Temporal(TemporalType.DATE)
	private Date data_nascimento;

	private String nome;
	private String genero;
	private String sobrenome;
	private String CEP;
	private String cidade;
	private String pais;
	private String preferencias;
	private String metas;
	private String email;
	private String senha;
	private String telefone;

	@Lob
	private byte[] foto; // Foto do cliente

	public ClienteDTO(String CPF, String email, String senha, String nome, String sobrenome, Date data_nascimento,
			String genero, String CEP, String cidade, String pais, String telefone) {
		this.CPF = CPF;
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.data_nascimento = data_nascimento;
		this.genero = genero;
		this.CEP = CEP;
		this.cidade = cidade;
		this.pais = pais;
		this.telefone = telefone;
		//this.CPF_cliente = CPF;
	}
	
	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public Date getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getPreferencias() {
		return preferencias;
	}

	public void setPreferencias(String preferencias) {
		this.preferencias = preferencias;
	}

	public String getMetas() {
		return metas;
	}

	public void setMetas(String metas) {
		this.metas = metas;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	
}
