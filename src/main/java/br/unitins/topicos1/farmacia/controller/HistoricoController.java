package br.unitins.topicos1.farmacia.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.unitins.topicos1.farmacia.application.Session;
import br.unitins.topicos1.farmacia.model.Compra;
import br.unitins.topicos1.farmacia.model.Usuario;
import br.unitins.topicos1.farmacia.repository.CompraRepository;

@RequestScoped
@Named
public class HistoricoController implements Serializable {

	private static final long serialVersionUID = -820126825745024512L;

	private List<Compra> listaCompra;

	@Inject
	private CompraRepository compraRepository;
	
	@PostConstruct
	public void init() {
		Usuario usuario = (Usuario) Session.getInstance().get("usuarioLogado");
		if (usuario == null) {
			listaCompra = new ArrayList<Compra>();
		} else {
			setListaCompra(compraRepository.buscarTodos(usuario));
			for (Compra compra : listaCompra) {
				System.out.println("Total Compra: " + compra.getTotal());
			}
		}
	}

	public List<Compra> getListaCompra() {
		return listaCompra;
	}

	public void setListaCompra(List<Compra> listaCompra) {
		this.listaCompra = listaCompra;
	}

}
