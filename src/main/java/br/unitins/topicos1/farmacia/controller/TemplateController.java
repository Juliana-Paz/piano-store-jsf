package br.unitins.topicos1.farmacia.controller;

import java.io.Serializable;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.unitins.topicos1.farmacia.application.Session;
import br.unitins.topicos1.farmacia.model.Compra;
import br.unitins.topicos1.farmacia.model.ItemCompra;
import br.unitins.topicos1.farmacia.model.Usuario;
import br.unitins.topicos1.farmacia.repository.RemedioRepository;

@Named
@ViewScoped
public class TemplateController implements Serializable {

	private static final long serialVersionUID = -747823450126578199L;
	
	private String nomeRemedio;
	
	@Inject
	private RemedioRepository remedioRepository;
	
	public String pesquisarRemedio() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("pesquisaRemedio", remedioRepository.buscarPeloNome(getNomeRemedio()));
		return "home.xhtml?faces-redirect=true";
	}
	
	public Usuario getUsuarioLogado() {
		Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		return (Usuario) session.get("usuarioLogado");
	}
	
	public String encerrarSessao() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login2.xhtml?faces-redirect=true";
	}

	public String getNomeRemedio() {
		return nomeRemedio;
	}

	public void setNomeRemedio(String nomeRemedio) {
		this.nomeRemedio = nomeRemedio;
	}
	
	public Integer getQuantidadeCarrinho() {
		Session session = Session.getInstance();
		Compra compra = (Compra) session.get("carrinho");
		
		if (compra == null || compra.getListaItemCompra() == null)
			return null;
		int counter = 0;
		
		for(ItemCompra itemCompra : compra.getListaItemCompra()) {
			counter += itemCompra.getQuantidade();
		}
		
		return counter;
		
	}
}
