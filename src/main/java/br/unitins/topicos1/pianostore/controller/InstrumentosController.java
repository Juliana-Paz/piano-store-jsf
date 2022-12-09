package br.unitins.topicos1.pianostore.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicos1.pianostore.model.Instrumento;
import br.unitins.topicos1.pianostore.repository.InstrumentoRepository;

@Named
@ViewScoped
public class InstrumentosController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8574201041530029028L;
	private List<Instrumento> listaInstrumento;
	private String termoFiltro = null;

	public List<Instrumento> getListaInstrumento() {
		if (listaInstrumento == null) {
			InstrumentoRepository repo = new InstrumentoRepository();
			listaInstrumento = repo.buscarTodos();
			if (listaInstrumento == null)
				listaInstrumento = new ArrayList<Instrumento>();
		}
		return listaInstrumento;
	}

	public String adicionar() {
		return "/admin/forminstrumento.xhtml?faces-redirect=true";
	}

	public List<Instrumento> filtrar() {
		InstrumentoRepository repo = new InstrumentoRepository();
		System.out.println(getTermoFiltro());
		listaInstrumento = repo.filtrarPorNome(getTermoFiltro());

		if (listaInstrumento == null)
			listaInstrumento = new ArrayList<Instrumento>();

		return listaInstrumento;
	}

	public String editar(Instrumento instrumento) {
		// flash scoped
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

		flash.put("flashInstrumento", instrumento);

		return "/admin/forminstrumento.xhtml?faces-redirect=true";
	}

	public void excluir(Instrumento instrumento) {
		InstrumentoRepository repo = new InstrumentoRepository();
		repo.deletar(instrumento);
		listaInstrumento = null;
	}

	public String getTermoFiltro() {
		return termoFiltro;
	}

	public void setTermoFiltro(String termoFiltro) {
		this.termoFiltro = termoFiltro;
	}

}
