package br.unitins.topicos1.pianostore.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicos1.pianostore.application.Session;
import br.unitins.topicos1.pianostore.application.Util;
import br.unitins.topicos1.pianostore.model.Usuario;
import br.unitins.topicos1.pianostore.repository.UsuarioRepository;

@Named
@ViewScoped
public class EditarPerfilController implements Serializable {

	private static final long serialVersionUID = -4197665569375141413L;
	private Usuario usuario = null;

	public EditarPerfilController() {
		Session session = Session.getInstance();
		usuario = (Usuario) session.get("usuarioLogado");
		setUsuario(usuario);
	}

	public String salvar() {
		UsuarioRepository repo = new UsuarioRepository();
		try {
			repo.salvar(getUsuario());
		} catch (Exception e) {
			Util.addErrorMessage(e.getMessage());
			e.printStackTrace();
			return null;
		}
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.setKeepMessages(true);

		Util.addInfoMessage("Usuario salvo com sucesso.");

		return cancelar();
	}

	public String cancelar() {
		return "/perfil.xhtml?faces-redirect=true";
	}

	public Usuario getUsuario() {
		Session session = Session.getInstance();
		usuario = (Usuario) session.get("usuarioLogado");
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
