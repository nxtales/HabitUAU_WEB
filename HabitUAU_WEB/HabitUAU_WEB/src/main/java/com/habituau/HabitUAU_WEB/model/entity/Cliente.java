package com.habituau.HabitUAU_WEB.model.entity;

import java.util.Optional;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Cliente {

	@Id
	@Column(length = 11)
	private String CPF;

	@Temporal(TemporalType.DATE)
	private Date data_nascimento;

	private String nome;
	private String genero;
	private String sobrenome;
	private String CEP;
	private String cidade;
	private String pais;
	private String preferencias;
	private String metas;

	@Column(unique = true)
	private String email;

	private String senha;
	private String telefone;

	@Lob
	private byte[] foto; // Foto do cliente

	private String CPF_cliente;
	
	/*public Cliente(String email2, String password, String nome2, String sobrenome2, Date dataNascimento2,
			String genero2, String cep2, String cidade2, String pais2, String telefone2) {
		// TODO Auto-generated constructor stub
	}*/

	 // Construtor padrão
    public Cliente() {
        // Construtor vazio para o Hibernate
    }
	
	public Cliente(String CPF, String email, String senha, String nome, String sobrenome, Date data_nascimento,
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
		this.CPF_cliente = CPF;
	}

	// Getters e Setters
	public String getCpf() {
		return CPF;
	}

	public void setCpf(String cpf) {
		this.CPF = cpf;
	}

	public Date getDataNascimento() {
		return data_nascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.data_nascimento = dataNascimento;
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

	public String getCep() {
		return CEP;
	}

	public void setCep(String cep) {
		this.CEP = cep;
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

	@Override
	public int hashCode() {
		return Objects.hash(CPF, email, nome, senha, sobrenome, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(CPF, other.CPF) && Objects.equals(email, other.email)
				&& Objects.equals(nome, other.nome) && Objects.equals(senha, other.senha)
				&& Objects.equals(sobrenome, other.sobrenome) && Objects.equals(telefone, other.telefone);
	}

	@Override
	public String toString() {
		return "Cliente [cpf=" + CPF + ", dataNascimento=" + data_nascimento + ", nome=" + nome + ", genero="
				+ genero + ", sobrenome=" + sobrenome + ", cep=" + CEP + ", cidade=" + cidade + ", pais=" + pais
				+ ", email=" + email + ", senha=" + senha + ", telefone=" + telefone + ", foto=" + Arrays.toString(foto)
				+ "]";
	}

	public void setPreferencias(String preferencias) {
		// TODO Auto-generated method stub
		this.preferencias = preferencias;
	}

	public void setMetas(String metas) {
		// TODO Auto-generated method stub
		this.metas = metas;
	}

	public String getMetas() {
		// TODO Auto-generated method stub
		return metas;
	}

	public String getPreferencias() {
		// TODO Auto-generated method stub
		return preferencias;
	}

}
