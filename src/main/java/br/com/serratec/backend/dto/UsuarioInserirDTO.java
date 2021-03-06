package br.com.serratec.backend.dto;

import br.com.serratec.backend.model.Usuario;

public class UsuarioInserirDTO {
	
	private String nome;
	private String email;
	private String senha;
	
	public UsuarioInserirDTO(Usuario usuario) {
		super();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
	}
	
	public UsuarioInserirDTO() {
		super();
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
	
}
