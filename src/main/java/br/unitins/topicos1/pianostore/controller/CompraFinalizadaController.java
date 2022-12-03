package br.unitins.topicos1.pianostore.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicos1.pianostore.application.Util;
import br.unitins.topicos1.pianostore.model.Compra;

@Named
@ViewScoped
public class CompraFinalizadaController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5517921013232067512L;

	private Compra compra;

	public CompraFinalizadaController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("flashCompraFinalizada");
		setCompra((Compra) flash.get("flashCompraFinalizada"));
	}
	
	
	public void irParaHistorico() {
		Util.redirect("historico.xhtml");
	}
	
	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

}
