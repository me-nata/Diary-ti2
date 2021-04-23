/*
 * Auhtor: Bryan Jonathan Melo De Oliveira
 */
//package com.ti2cc;

public class Usuario {
	private int id;
	private String nome;
	private String email;
	private String senha;
	private int idade;
	private boolean premium;
	
	public Usuario() {
		this.id = -1;
		this.nome = "";
		this.email = "";
		this.senha = "";
		this.idade = -1;
		this.premium = false;
	}
	
	public Usuario(int id, String nome, String email, String senha, int idade, boolean premium) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.idade = idade;
		this.premium = premium;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
     
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
	
	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public boolean getPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	@Override
	public String toString() {
		return "Usuario [ id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", idade=" + idade + ", premium=" + premium + " ]";
	}
	
}
