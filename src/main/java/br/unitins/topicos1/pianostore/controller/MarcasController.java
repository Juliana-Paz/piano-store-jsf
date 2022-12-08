package br.unitins.topicos1.pianostore.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicos1.pianostore.model.Marca;
import br.unitins.topicos1.pianostore.repository.MarcaRepository;

@Named
@ViewScoped
public class MarcasController implements Serializable {

	private static final long serialVersionUID = 8826311835173736094L;

	private List<Marca> listaMarca;
	private String termoFiltro = null;

	public List<Marca> getListaMarca() {
		if (listaMarca == null) {
			MarcaRepository repo = new MarcaRepository();
			listaMarca = repo.buscarTodos();
			if (listaMarca == null)
				listaMarca = new ArrayList<Marca>();
		}
		return listaMarca;
	}

	public String adicionar() {
		return "/admin/formmarca.xhtml?faces-redirect=true";
	}

	public List<Marca> filtrar() {
		MarcaRepository repo = new MarcaRepository();
		System.out.println(getTermoFiltro());
		listaMarca = repo.filtrarPorNome(getTermoFiltro());

		if (listaMarca == null)
			listaMarca = new ArrayList<Marca>();

		return listaMarca;
	}

	public String editar(Marca marca) {
		// flash scoped
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

		flash.put("flashMarca", marca);

		return "/admin/formmarca.xhtml?faces-redirect=true";
	}

	public void excluir(Marca marca) {
		MarcaRepository repo = new MarcaRepository();
		repo.deletar(marca);
		listaMarca = null;
	}

	public String getTermoFiltro() {
		return termoFiltro;
	}

	public void setTermoFiltro(String termoFiltro) {
		this.termoFiltro = termoFiltro;
	}

}
