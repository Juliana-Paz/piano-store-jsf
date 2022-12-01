package br.unitins.topicos1.pianostore.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ItemCompra extends DefaultEntity {
	
	private int quantidade;
	private Double preco;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Compra compra;
	
	@ManyToOne
	@JoinColumn(name = "id_remedio")
	private Remedio remedio;

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Remedio getRemedio() {
		return remedio;
	}

	public void setRemedio(Remedio remedio) {
		this.remedio = remedio;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	
}
