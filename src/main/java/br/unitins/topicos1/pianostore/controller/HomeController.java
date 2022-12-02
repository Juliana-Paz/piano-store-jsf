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
import br.unitins.topicos1.pianostore.model.ItemCompra;
import br.unitins.topicos1.pianostore.model.Remedio;
import br.unitins.topicos1.pianostore.repository.RemedioRepository;

@RequestScoped
@Named
public class HomeController {

	@Inject
	private RemedioRepository repository;
	private List<Remedio> listaRemedio;

	@PostConstruct
	public void init() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		Object resultado = flash.get("pesquisaRemedio");
		// verificando se teve consulta de remedio pela pesquisa no template
		if (resultado != null)
			setListaRemedio((ArrayList<Remedio>) resultado);
		else
			setListaRemedio(repository.buscarTodos());
	}

	public void adicionarCarrinho(Remedio remedio) {

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
				.filter(item -> item.getRemedio().equals(remedio)).findAny();

		ItemCompra item = opItem.orElse(new ItemCompra());

		item.setPreco(remedio.getPreco());
		item.setRemedio(remedio);
		item.setQuantidade(item.getQuantidade() + 1);

		// buscando se existe um item no carrinho para alterar
		int indice = -1;
		for (int index = 0; index < carrinho.getListaItemCompra().size(); index++) {
			if (carrinho.getListaItemCompra().get(index).getRemedio().equals(remedio)) {
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

		Util.addInfoMessage(item.getRemedio().getNome() + " adicionado ao carrinho.");

	}

	public List<Remedio> getListaRemedio() {
		return listaRemedio;
	}

	public void setListaRemedio(List<Remedio> listaRemedio) {
		this.listaRemedio = listaRemedio;
	}

}
