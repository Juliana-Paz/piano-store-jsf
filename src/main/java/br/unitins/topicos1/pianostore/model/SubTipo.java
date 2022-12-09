package br.unitins.topicos1.pianostore.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class SubTipo extends DefaultEntity {

	@NotBlank(message = "O nome deve ser informado.")
	private String nome;

	private Tipo tipo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

}
