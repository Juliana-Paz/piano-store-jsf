package br.unitins.topicos1.pianostore.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicos1.pianostore.application.Util;
import br.unitins.topicos1.pianostore.model.Marca;
import br.unitins.topicos1.pianostore.repository.MarcaRepository;

@Named
@ViewScoped
public class FormMarcaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5120960918849263518L;
	private Marca marca = null;

	public FormMarcaController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("flashMarca");
		setMarca((Marca) flash.get("flashMarca"));
	}


	public String salvar() {
		MarcaRepository repo = new MarcaRepository();
		try {
			
			repo.salvar(getMarca());
		} catch (Exception e) {
			Util.addErrorMessage(e.getMessage());
			e.printStackTrace();
			return null;
		}
		// PARA HABILITAR O ENVIO DE MENSAGEM ENTRE TELAS DIFERENTES
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.setKeepMessages(true);

		Util.addInfoMessage("Marca salva com sucesso.");

		// o cancelar retorna para a pagina anterior
		return cancelar();
	}

	public String cancelar() {
		return "/admin/marcas.xhtml?faces-redirect=true";
	}

	public Marca getMarca() {
		if (marca == null)
			marca = new Marca();
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

}
