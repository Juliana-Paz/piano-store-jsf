package br.unitins.topicos1.pianostore.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

import br.unitins.topicos1.pianostore.application.Session;
import br.unitins.topicos1.pianostore.application.Util;
import br.unitins.topicos1.pianostore.model.Compra;
import br.unitins.topicos1.pianostore.model.Instrumento;
import br.unitins.topicos1.pianostore.model.ItemCompra;
import br.unitins.topicos1.pianostore.repository.InstrumentoRepository;

@RequestScoped
@Named
public class HomeController {

	@Inject
	private InstrumentoRepository repository;
	private List<Instrumento> listaInstrumento;

	@PostConstruct
	public void init() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		Object resultado = flash.get("pesquisaInstrumento");
		if (resultado != null)
			setListaInstrumento((ArrayList<Instrumento>) resultado);
		else
			setListaInstrumento(repository.buscarTodos());
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

	public List<Instrumento> getListaInstrumento() {
		return listaInstrumento;
	}

	public void setListaInstrumento(List<Instrumento> listaInstrumento) {
		this.listaInstrumento = listaInstrumento;
	}

}
