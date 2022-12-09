package br.unitins.topicos1.pianostore.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicos1.pianostore.application.Util;
import br.unitins.topicos1.pianostore.model.Instrumento;
import br.unitins.topicos1.pianostore.model.Perfil;
import br.unitins.topicos1.pianostore.model.SubTipo;
import br.unitins.topicos1.pianostore.repository.EstadoRepository;
import br.unitins.topicos1.pianostore.repository.InstrumentoRepository;
import br.unitins.topicos1.pianostore.repository.SubTipoRepository;

@Named
@ViewScoped
public class FormInstrumentoController implements Serializable {

	private static final long serialVersionUID = -4197665569375141413L;
	private Instrumento instrumento = null;
	private List<SubTipo> listaSubTipo;

	public FormInstrumentoController() {
		// obtendo um instrumento do flash scoped
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("flashInstrumento");
		setInstrumento((Instrumento) flash.get("flashInstrumento"));
	}

	public Perfil[] getListaPerfil() {
		return Perfil.values();
	}

	public String salvar() {
		InstrumentoRepository repo = new InstrumentoRepository();
		try {
			repo.salvar(getInstrumento());
		} catch (Exception e) {
			Util.addErrorMessage(e.getMessage());
			e.printStackTrace();
			return null;
		}
		// PARA HABILITAR O ENVIO DE MENSAGEM ENTRE TELAS DIFERENTES
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.setKeepMessages(true);

		Util.addInfoMessage("Instrumento salvo com sucesso.");

		// o cancelar retorna para a pagina anterior
		return cancelar();
	}

	public String cancelar() {
		return "/admin/instrumentos.xhtml?faces-redirect=true";
	}

	public Instrumento getInstrumento() {
		if (instrumento == null)
			instrumento = new Instrumento();
		return instrumento;
	}

	public void setInstrumento(Instrumento instrumento) {
		this.instrumento = instrumento;
	}

	public List<SubTipo> getListaSubTipo() {
		if (listaSubTipo == null) { 
			SubTipoRepository repo = new SubTipoRepository();
			listaSubTipo = repo.buscarTodos();
		}
		return listaSubTipo;
	}

	public void setListaSubTipo(List<SubTipo> listaSubTipo) {
		this.listaSubTipo = listaSubTipo;
	}

}
