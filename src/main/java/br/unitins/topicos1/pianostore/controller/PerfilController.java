package br.unitins.topicos1.pianostore.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicos1.pianostore.application.Session;
import br.unitins.topicos1.pianostore.model.Usuario;

@ViewScoped
@Named
public class PerfilController implements Serializable {

	private static final long serialVersionUID = 1607320074278137212L;

	Usuario usuario;

	public Usuario getUsuario() {
		Session session = Session.getInstance();
		usuario = (Usuario) session.get("usuarioLogado");
		return usuario;
	}
	

	public String editarPerfil() {
		return "/editarperfil.xhtml?faces-redirect=true";
	}

}
