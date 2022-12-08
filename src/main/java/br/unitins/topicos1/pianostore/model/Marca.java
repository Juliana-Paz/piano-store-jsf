package br.unitins.topicos1.pianostore.model;

import javax.persistence.Entity;

@Entity
public class Marca extends DefaultEntity {

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
