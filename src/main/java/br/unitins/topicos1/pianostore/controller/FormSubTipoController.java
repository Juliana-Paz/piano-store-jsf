package br.unitins.topicos1.pianostore.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicos1.pianostore.application.Util;
import br.unitins.topicos1.pianostore.model.Perfil;
import br.unitins.topicos1.pianostore.model.SubTipo;
import br.unitins.topicos1.pianostore.model.Tipo;
import br.unitins.topicos1.pianostore.repository.SubTipoRepository;

@Named
@ViewScoped
public class FormSubTipoController implements Serializable {

	private static final long serialVersionUID = -8169244642073482150L;
	private SubTipo subTipo = null;

	public FormSubTipoController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("flashSubTipo");
		setSubTipo((SubTipo) flash.get("flashSubTipo"));
	}

	public Tipo[] getListaTipo() {
		return Tipo.values();
	}

	public String salvar() {
		SubTipoRepository repo = new SubTipoRepository();
		try {
			repo.salvar(getSubTipo());
		} catch (Exception e) {
			Util.addErrorMessage(e.getMessage());
			e.printStackTrace();
			return null;
		}
		// PARA HABILITAR O ENVIO DE MENSAGEM ENTRE TELAS DIFERENTES
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.setKeepMessages(true);

		Util.addInfoMessage("SubTipo salvo com sucesso.");

		return cancelar();
	}

	public String cancelar() {
		return "/admin/subtipos.xhtml?faces-redirect=true";
	}

	public SubTipo getSubTipo() {
		if (subTipo == null)
			subTipo = new SubTipo();
		return subTipo;
	}

	public void setSubTipo(SubTipo subTipo) {
		this.subTipo = subTipo;
	}

}
