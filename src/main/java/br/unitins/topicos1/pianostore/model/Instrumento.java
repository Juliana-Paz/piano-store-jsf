package br.unitins.topicos1.pianostore.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Instrumento extends DefaultEntity {

	private String nome;
	private String descricao;
	private Double preco;
	private Integer estoque;
	
	@ManyToOne
	@JoinColumn(name = "id_subtipo")
	private SubTipo subTipo;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public SubTipo getSubTipo() {
		return subTipo;
	}

	public void setSubTipo(SubTipo subTipo) {
		this.subTipo = subTipo;
	}
	
	

}
