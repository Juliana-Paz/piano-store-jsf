package br.unitins.topicos1.pianostore.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicos1.pianostore.application.Session;
import br.unitins.topicos1.pianostore.application.Util;
import br.unitins.topicos1.pianostore.model.Compra;
import br.unitins.topicos1.pianostore.model.Instrumento;
import br.unitins.topicos1.pianostore.model.ItemCompra;
import br.unitins.topicos1.pianostore.model.Usuario;

@ViewScoped
@Named
public class CarrinhoController implements Serializable {

	private static final long serialVersionUID = -3985985036777802588L;
	private Compra carrinho;

	public List<ItemCompra> getItensCarrinho() {
		Session session = Session.getInstance();

		carrinho = (Compra) session.get("carrinho");

		if (carrinho == null)
			return new ArrayList<ItemCompra>();

		return carrinho.getListaItemCompra();
	}

	public void finalizarCompra() {
		if (Session.getInstance().get("usuarioLogado") == null)
			Util.redirect("login.xhtml");

		Compra carrinho = (Compra) Session.getInstance().get("carrinho");

		if (carrinho == null || carrinho.getListaItemCompra() == null || carrinho.getListaItemCompra().isEmpty()) {
			Util.addWarnMessage("Adicione um item no carrinho antes de concluir a compra.");
			return;
		}

		Util.redirect("finalizarcompra.xhtml");
	}

	public Double getTotalCarrinho() {
		Session session = Session.getInstance();
		Compra compra = (Compra) session.get("carrinho");

		if (compra == null || compra.getListaItemCompra() == null)
			return null;
		double valorTotal = 0;

		for (ItemCompra itemCompra : compra.getListaItemCompra()) {
			valorTotal += (itemCompra.getQuantidade() * itemCompra.getPreco());
		}

		return valorTotal;

	}

	public Usuario getUsuarioLogado() {
		Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		return (Usuario) session.get("usuarioLogado");
	}

	public void adicionarCarrinho(Instrumento instrumento) {
		Compra carrinho;
		Session session = Session.getInstance();
		if (session.get("carrinho") != null) {
			carrinho = (Compra) session.get("carrinho");
		} else {
			carrinho = new Compra();
		}

		// verificando se existe itens de compra
		if (carrinho.getListaItemCompra() == null)
			carrinho.setListaItemCompra(new ArrayList<ItemCompra>());

		// buscando um item na lista do carrinho
		Optional<ItemCompra> opItem = carrinho.getListaItemCompra().stream()
				.filter(item -> item.getInstrumento().equals(instrumento)).findAny();

		ItemCompra item = opItem.orElse(new ItemCompra());

		if(item.getQuantidade() + 1 > instrumento.getEstoque()) {
			Util.addErrorMessage( "Não há itens no estoque.");
			return;
		}
		
		
		item.setPreco(instrumento.getPreco());
		item.setInstrumento(instrumento);
		item.setQuantidade(item.getQuantidade() + 1);

		// buscando se existe um item no carrinho para alterar
		int indice = -1;
		for (int index = 0; index < carrinho.getListaItemCompra().size(); index++) {
			if (carrinho.getListaItemCompra().get(index).getInstrumento().equals(instrumento)) {
				indice = index;
				break;
			}
		}

		if (indice >= 0)
			carrinho.getListaItemCompra().set(indice, item);
		else
			carrinho.getListaItemCompra().add(item);

		// adicionando na sessao
		session.put("carrinho", carrinho);

		Util.addInfoMessage(item.getInstrumento().getNome() + " adicionado ao carrinho.");
	}

	public void removerCarrinho(Instrumento instrumento) {
		Compra carrinho;
		Session session = Session.getInstance();
		if (session.get("carrinho") != null) {
			carrinho = (Compra) session.get("carrinho");
		} else {
			carrinho = new Compra();
		}

		if (carrinho.getListaItemCompra() == null)
			carrinho.setListaItemCompra(new ArrayList<ItemCompra>());

		Optional<ItemCompra> opItem = carrinho.getListaItemCompra().stream()
				.filter(item -> item.getInstrumento().equals(instrumento)).findAny();
		
		ItemCompra item = opItem.orElse(new ItemCompra());

		int novaQuantidade = item.getQuantidade() - 1;

		item.setQuantidade(novaQuantidade);

		int indice = -1;
		for (int index = 0; index < carrinho.getListaItemCompra().size(); index++) {
			if (carrinho.getListaItemCompra().get(index).getInstrumento().equals(instrumento)) {
				indice = index;
				break;
			}
		}

		if (novaQuantidade >=1)
			carrinho.getListaItemCompra().set(indice, item);
		else 
			carrinho.getListaItemCompra().remove(indice);

		session.put("carrinho", carrinho);

		Util.addInfoMessage(item.getInstrumento().getNome() + " removido do carrinho.");
	}
}
