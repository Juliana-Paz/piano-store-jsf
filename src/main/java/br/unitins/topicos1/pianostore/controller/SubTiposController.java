package br.unitins.topicos1.pianostore.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicos1.pianostore.model.SubTipo;
import br.unitins.topicos1.pianostore.repository.SubTipoRepository;

@Named
@ViewScoped
public class SubTiposController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5187866552997399904L;
	private List<SubTipo> listaSubTipo;
	private String termoFiltro = null;

	public List<SubTipo> getListaSubTipo() {
		if (listaSubTipo == null) {
			SubTipoRepository repo = new SubTipoRepository();
			listaSubTipo = repo.buscarTodos();
			if (listaSubTipo == null)
				listaSubTipo = new ArrayList<SubTipo>();
		}
		return listaSubTipo;
	}

	public String adicionar() {
		return "/admin/formsubtipo.xhtml?faces-redirect=true";
	}

	public List<SubTipo> filtrar() {
		SubTipoRepository repo = new SubTipoRepository();
		System.out.println(getTermoFiltro());
		listaSubTipo = repo.filtrarPorNome(getTermoFiltro());

		if (listaSubTipo == null)
			listaSubTipo = new ArrayList<SubTipo>();

		return listaSubTipo;
	}

	public String editar(SubTipo subTipo) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

		flash.put("flashSubTipo", subTipo);

		return "/admin/formsubtipo.xhtml?faces-redirect=true";
	}

	public void excluir(SubTipo subTipo) {
		SubTipoRepository repo = new SubTipoRepository();
		repo.deletar(subTipo);
		listaSubTipo = null;
	}

	public String getTermoFiltro() {
		return termoFiltro;
	}

	public void setTermoFiltro(String termoFiltro) {
		this.termoFiltro = termoFiltro;
	}

}
